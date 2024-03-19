<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width:200px" placeholder="请输入老人姓名" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-input style="width:200px" placeholder="请输入服务地址" suffix-icon="el-icon-message" class="ml-5" v-model="address"></el-input>
      <el-input style="width:200px" placeholder="请输入服务项目" suffix-icon="el-icon-position" class="ml-5" v-model="project"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" >新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch">

        <el-button type="danger"slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload action="http://localhost:9090/help/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml-5">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="工单号" width="80"></el-table-column>
      <el-table-column prop="name" label="老人姓名"></el-table-column>
      <el-table-column prop="video" label="电话录音"></el-table-column>
      <el-table-column prop="phone" label="老人电话" ></el-table-column>
      <el-table-column prop="address" label="服务地址"></el-table-column>
      <el-table-column prop="status" label="工单状态"></el-table-column>
      <el-table-column prop="project" label="服务项目"></el-table-column>
      <el-table-column prop="pay" label="付款方式" ></el-table-column>
      <el-table-column prop="fee" label="服务金额"></el-table-column>
      <el-table-column prop="service" label="服务人员"></el-table-column>
      <el-table-column prop="admin" label="接单人员"></el-table-column>
      <el-table-column prop="time" label="下单时间"></el-table-column>
      <el-table-column label="操作" width="180px">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" @click="del(scope.row.id)" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
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
    <el-dialog title="服务信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="老人姓名">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="老人电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="服务地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="服务项目">
          <el-input v-model="form.project" autocomplete="off"></el-input>
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
  name: "Help",
  data(){
    return{
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:10,
      name:"",
      project:"",
      address:"",
      form:{},
      dialogFormVisible:false,
      multipleSelection:[],
      roles: [],
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
      this.request.get("/help/page?",{
        params: {
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
          project:this.project,
          address:this.address
        }
      }).then(res => {
        console.log(res)

        this.tableData = res.data.records
        this.total = res.data.total
      })
      this.request.get("/role").then(res => {
        this.roles = res.data
      })
    },
    save(){
      request.post("/help",this.form).then(res =>{
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
      this.name = ""
      this.project = ""
      this.address = ""
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
    del(id){
      request.delete("/help/" + id).then(res => {
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
      request.post("/help/del/batch",ids).then(res => {
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
      window.open("http://localhost:9090/help/export")
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
