<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width:200px" placeholder="请输入审核状态" suffix-icon="el-icon-search" v-model="status"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <er-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch">
        <el-button type="danger"slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </er-popconfirm>
      <el-button type="primary" @click="exp" class="ml-5">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="activityId" label="活动名称" ></el-table-column>
      <el-table-column prop="userId" label="预约人" ></el-table-column>
      <el-table-column prop="time" label="预约时间"></el-table-column>
      <el-table-column prop="status" label="审核状态"></el-table-column>
      <el-table-column label="取消" width="150" fixed="right" v-if="user.role === 'ROLE_USER'">
        <template slot-scope="scope" >
          <el-button type="primary" @click="cancel(scope.row.id)" v-if="user.role === 'ROLE_USER'">取消预约<i class="el-icon-remove-outline"></i></el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" fixed="right" v-if="user.role === 'ROLE_ADMIN'">
        <template slot-scope="scope">
          <el-button type="success" @click="handleAudit(scope.row)" v-if="user.role === 'ROLE_ADMIN'">审核</el-button>
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

    <div>
    <el-dialog title="审核状态" :visible.sync="auditVisible" width="30%">
      <el-form label-width="140px" size="small">
          <el-radio v-model="form.status" label="待审核"></el-radio>
          <el-radio v-model="form.status" label="审核通过"></el-radio>
          <el-radio v-model="form.status" label="审核拒绝"></el-radio>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="auditVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "Reserve",
  data(){
    return{
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:5,
      status:"",
      form:{},
      dialogFormVisible:false,
      auditVisible:false,
      multipleSelection:[],
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")):{},
      headerBg:'headerBg'
    }
  },
  created() {
    //请求分页查询数据
    this.load()
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
    load() {
      request.get("/reserve/page?",{
        params: {
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          status:this.status
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.data.records
        this.total = res.data.total

      })
    },
    save(){
      request.post("/reserve",this.form).then(res =>{
        if(res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.auditVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    reset(){
      this.status = ""
      this.load()
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleAudit(obj){
      this.form = obj
      this.auditVisible = true

    },
    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
    },
    handleReserve(){
      this.auditVisible = true
    },
    del(id){
      request.delete("/reserve/" + id).then(res => {
        if(res.data){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    cancel(id){
      request.delete("/reserve/" + id).then(res => {
        if(res.data){
          this.$message.success("取消预约成功")
          this.load()
        }else{
          this.$message.error("取消预约失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      request.post("/reserve/del/batch",ids).then(res => {
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
      window.open("http://localhost:9090/reserve/export")
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
