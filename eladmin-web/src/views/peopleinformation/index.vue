<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">性别（男/女）</label>
        <el-input v-model="query.sex" clearable placeholder="性别（男/女）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">出生日期（年/月/日）</label>
        <el-input v-model="query.brthdat" clearable placeholder="出生日期（年/月/日）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">年龄</label>
        <el-input v-model="query.age" clearable placeholder="年龄" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">体重（kg）</label>
        <el-input v-model="query.wgt" clearable placeholder="体重（kg）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">身高（cm）</label>
        <el-input v-model="query.hgt" clearable placeholder="身高（cm）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="患者编号" prop="subjid">
            <el-input v-model="form.subjid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="性别（男/女）">
            <el-radio v-model="form.sex" v-for="item in dict.sex" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="出生日期（年/月/日）">
            <el-date-picker v-model="form.brthdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="体重（kg）">
            <el-input v-model="form.wgt" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="身高（cm）">
            <el-input v-model="form.hgt" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="subjid" label="患者编号" />
        <el-table-column prop="sex" label="性别（男/女）">
          <template slot-scope="scope">
            {{ dict.label.sex[scope.row.sex] }}
          </template>
        </el-table-column>
        <el-table-column prop="brthdat" label="出生日期（年/月/日）" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="wgt" label="体重（kg）" />
        <el-table-column prop="hgt" label="身高（cm）" />
        <el-table-column v-if="checkPer(['admin','peopleinformation:edit','peopleinformation:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudPeopleinformation from '@/api/peopleinformation'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, name: null, subjid: null, sex: null, brthdat: null, age: null, wgt: null, hgt: null }
export default {
  name: 'Peopleinformation',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['sex'],
  cruds() {
    return CRUD({ title: '人口学资料', url: 'api/peopleinformation', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudPeopleinformation }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'peopleinformation:add'],
        edit: ['admin', 'peopleinformation:edit'],
        del: ['admin', 'peopleinformation:del']
      },
      rules: {
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
        subjid: [
          { required: true, message: '患者编号不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'sex', display_name: '性别（男/女）' },
        { key: 'brthdat', display_name: '出生日期（年/月/日）' },
        { key: 'age', display_name: '年龄' },
        { key: 'wgt', display_name: '体重（kg）' },
        { key: 'hgt', display_name: '身高（cm）' }
      ]
    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
