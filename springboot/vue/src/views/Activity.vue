<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width:200px" placeholder="请输入活动名称" suffix-icon="el-icon-search" v-model="actname"></el-input>
      <el-input style="width:200px" placeholder="请输入活动地点" suffix-icon="el-icon-message" class="ml-5" v-model="location"></el-input>
      <el-input style="width:200px" placeholder="请输入活动类别" suffix-icon="el-icon-search" v-model="actsort"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" v-if="user.role === 'ROLE_ADMIN'">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch">

        <el-button type="danger"slot="reference" v-if="user.role === 'ROLE_ADMIN'">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload action="http://localhost:9090/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5" v-if="user.role === 'ROLE_ADMIN'">导入<i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml-5" v-if="user.role === 'ROLE_ADMIN'">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="actname" label="活动名称" ></el-table-column>
      <el-table-column prop="typeId" label="活动类别" ></el-table-column>
      <el-table-column prop="descr" label="活动描述" ></el-table-column>
      <el-table-column prop="rule" label="活动规则"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="150px"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="150px"></el-table-column>
      <el-table-column prop="creatorId" label="发起人id"></el-table-column>
      <el-table-column prop="creator" label="发起人"></el-table-column>
      <el-table-column prop="location" label="活动地点"></el-table-column>
      <el-table-column prop="fee" label="活动经费"></el-table-column>
      <el-table-column prop="nums" label="活动总人数"></el-table-column>
      <el-table-column prop="leftNums" label="剩余可预约活动人数"></el-table-column>
      <el-table-column prop="status" label="活动状态">
        <template #default="scope">
          <el-tag v-if="scope.row.status === '未开始'">未开始</el-tag>
          <el-tag v-if="scope.row.status === '进行中'" type="success">进行中</el-tag>
          <el-tag v-if="scope.row.status === '已结束'" type="danger">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="img" label="图片">
        <template #default="scope">
          <el-image preview-teleproted :src="scope.row.img" ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="预约" fixed="right" width="120" v-if="user.role === 'ROLE_USER'">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleReserve(scope.row)" v-if="user.role === 'ROLE_USER'" >预约<i class="el-icon-alarm-clock"></i></el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" fixed="right" v-if="user.role === 'ROLE_ADMIN'">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)" v-if="user.role === 'ROLE_ADMIN'">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" @click="del(scope.row.id)" slot="reference" v-if="user.role === 'ROLE_ADMIN'">删除<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="活动信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="140px" size="small">
        <el-form-item label="活动名称">
          <el-input v-model="form.actname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动描述">
          <el-input v-model="form.descr" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="开始时间">
            <el-date-picker
                v-model="form.startTime"
                type="datetime"
                value-format = 'yyyy-MM-dd hh:mm:ss'
                placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker
              v-model="form.endTime"
              type="datetime"
              value-format = 'yyyy-MM-dd hh:mm:ss'
              placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动总人数" >
          <el-input v-model="form.nums" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="剩余可预约活动人数">
          <el-input v-model="form.leftNums" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动地点">
          <el-input v-model="form.location" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动经费">
          <el-input v-model="form.fee" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动类型">
          <el-input v-model="form.typeId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动照片">
          <el-upload
              v-model="form.img"
              autocomplete="off">
<!--              action="http://localhost:8080/api/files/upload"-->
<!--              :on-success="successUpload"-->
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="活动规则">
          <el-input v-model="form.rule" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动状态">
          <el-input v-model="form.status" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "Activity",
  data(){
    return{
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:5,
      actname:"",
      location:"",
      form:{},
      dialogFormVisible:false,
      multipleSelection:[],
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")):{},
      headerBg:'headerBg',
      typeObjs:[]
    }
  },
  created() {
    //请求分页查询数据
    this.load()
    this.findTypes()
  },
  methods: {
    collapse(){
      //点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){//收缩
        this.sideWidth =64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      }
      else{//展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    findTypes(){
      request.get("/type").then(res => {
        if(res.data === '200'){
          this.typeObjs = res.data
        }else{
          this.$message.error("查找失败")
        }
      })
    },
    load() {
      request.get("/activity/page?",{
        params: {
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          actname:this.actname,
          location:this.location
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.data.records
        this.total = res.data.total

      })
    },
    save(){
      request.post("/activity",this.form).then(res =>{
        if(res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    reset(){
      this.actname = ""
      this.location = ""
      this.load()
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
    },
    handleReserve(row){
      this.form = {};
      let param = {activityId:row.id,userId:this.user.id}
      request.post("/reserve",param).then(res => {
        if(res.code === '200'){
          this.$message.success("预约成功")
        }else{
          this.$message.error("预约失败")
        }
      })
    },
    del(id){
      request.delete("/activity/" + id).then(res => {
        if(res.data){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      request.post("/activity/del/batch",ids).then(res => {
        if(res.data){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    exp(){
      window.open("http://localhost:9090/activity/export")
    },
    handleExcelImportSuccess(){
      this.$message.success("导入成功")
      this.load()
    }
  },
}
</script>

<style>
.headerBg{
  background: #eee !important;
}
</style>
