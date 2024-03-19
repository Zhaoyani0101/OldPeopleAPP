<template>
  <div>
    <div style="margin: 10px 0">
      文章标题：
      <el-input style="width:200px" placeholder="请输入文章标题" suffix-icon="el-icon-search" v-model="name"></el-input>
      信息状态：
      <el-select  placeholder="请选择" v-model="status">
        <el-option label="未读" value="0"></el-option>
        <el-option label="已读" value="1"></el-option>
      </el-select>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" >发布通告<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch">

        <el-button type="danger"slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="文章标题"></el-table-column>
      <el-table-column prop="content" label="文章内容">
        <template slot-scope="scope">
          <el-button @click="view(scope.row);check(scope.row.id)" type="primary">查看内容</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="user" label="发布人" ></el-table-column>
      <el-table-column prop="time" label="发布时间"></el-table-column>
      <el-table-column prop="status" label="信息状态">
        <template slot-scope="scope">
          <p v-if="tableData[scope.$index].status==0"><el-tag type="danger" size="medium" effect="dark">未读</el-tag></p >
          <p  v-if="tableData[scope.$index].status==1"><el-tag type="info"  size="medium" effect="dark">已读</el-tag></p >
        </template>
      </el-table-column>
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
    <el-dialog title="文章信息" :visible.sync="dialogFormVisible" width="60%">
      <el-form label-width="80px" size="small">
        <el-form-item label="文章标题">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="文章内容">
          <mavon-editor ref="md"v-model="form.content" :ishljs="true" @imgAdd="imgAdd"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="通知信息" :visible.sync="viewDialogVis" style="height: 90%;width: 100%" >
      <el-descriptions title="通知信息">
        <el-descriptions-item label="来自">
          <el-tag size="small">{{content.user}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="时间">{{content.time}}</el-descriptions-item>
      </el-descriptions>
      <div>
        <el-button style="margin-right: 50px;display: block" type="primary" @click="download(content.url)" v-if="content.url"  round >下载附件</el-button>
      </div>

      <div>
        <mavon-editor
            class="md"
            style="height: 100vh"
            :value="content.content"
            :subfield="false"
            :defaultOpen="'preview'"
            :toolbarsFlag="false"
            :editable="false"
            :scrollStyle="true"
            :ishljs="true"
        />
      </div>
    </el-dialog>

  </div>
</template>

<script>

import request from "@/utils/request";
import axios from "axios";
export default {
  name: "Article",
  data(){
    return{
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:10,
      name:"",
      form:{},
      dialogFormVisible:false,
      multipleSelection:[],
      roles: [],
      headerBg:'headerBg',
      content:'',
      viewDialogVis:false
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods: {
    view(content){
      this.content = content
      this.viewDialogVis = true
    },
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
    // 绑定@imgAdd event
    imgAdd(pos, $file) {
      let $vm = this.$refs.md
      // 第一步.将图片上传到服务器.
      const formData = new FormData();
      formData.append('file', $file);
      axios({
        url: 'http://localhost:9090/file/upload',
        method: 'post',
        data: formData,
        headers: {'Content-Type': 'multipart/form-data'},
      }).then((res) => {
        // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
        $vm.$img2Url(pos, res.data);
      })
    },
    load() {
      this.request.get("/article/page?",{
        params: {
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
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
    check(id){
      this.request.post("/article/update/"+id)
      this.load()
    },
    save(){
      request.post("/article",this.form).then(res =>{
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
      this.username = ""
      this.email = ""
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
      request.delete("/article/" + id).then(res => {
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
      request.post("/article/del/batch",ids).then(res => {
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
      window.open("http://localhost:9090/article/export")
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
