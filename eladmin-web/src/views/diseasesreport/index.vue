<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血气分析（是/否）</label>
        <el-input v-model="query.bgyn" clearable placeholder="血气分析（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">检查日期（年/月/日）</label>
        <el-input v-model="query.bgchkdat" clearable placeholder="检查日期（年/月/日）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血氧饱和度-检查结果</label>
        <el-input v-model="query.bg1res" clearable placeholder="血氧饱和度-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血氧饱和度-结果判定</label>
        <el-input v-model="query.bg1clsig" clearable placeholder="血氧饱和度-结果判定" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血氧分压-检查结果</label>
        <el-input v-model="query.bg2res" clearable placeholder="血氧分压-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血氧分压-结果判定</label>
        <el-input v-model="query.bg2clsig" clearable placeholder="血氧分压-结果判定" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血液酸碱度-检查结果</label>
        <el-input v-model="query.bg3res" clearable placeholder="血液酸碱度-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血液酸碱度-结果判定</label>
        <el-input v-model="query.bg3clsig" clearable placeholder="血液酸碱度-结果判定" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="患者编号">
            <el-input v-model="form.subjid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血气分析（是/否）">
            <el-radio v-model="form.bgyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）">
            <el-date-picker v-model="form.bgchkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧饱和度-检查结果">
            <el-input v-model="form.bg1res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧饱和度-单位">
            <el-input v-model="form.bg1unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧饱和度-结果判定">
            <el-input v-model="form.bg1clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧饱和度-备注">
            <el-input v-model="form.bg1oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧分压-检查结果">
            <el-input v-model="form.bg2res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧分压-单位">
            <el-input v-model="form.bg2unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧分压-结果判定">
            <el-input v-model="form.bg2clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血氧分压-备注">
            <el-input v-model="form.bg2oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血液酸碱度-检查结果">
            <el-input v-model="form.bg3res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血液酸碱度-单位">
            <el-input v-model="form.bg3unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血液酸碱度-结果判定">
            <el-input v-model="form.bg3clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血液酸碱度-备注">
            <el-input v-model="form.bg3oth" style="width: 370px;" />
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
        <el-table-column prop="subjid" label="患者编号" />
        <el-table-column prop="bgyn" label="血气分析（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.bgyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="bgchkdat" label="检查日期（年/月/日）" />
        <el-table-column prop="bg1res" label="血氧饱和度-检查结果" />
        <el-table-column prop="bg1unit" label="血氧饱和度-单位" />
        <el-table-column prop="bg1clsig" label="血氧饱和度-结果判定" />
        <el-table-column prop="bg1oth" label="血氧饱和度-备注" />
        <el-table-column prop="bg2res" label="血氧分压-检查结果" />
        <el-table-column prop="bg2unit" label="血氧分压-单位" />
        <el-table-column prop="bg2clsig" label="血氧分压-结果判定" />
        <el-table-column prop="bg2oth" label="血氧分压-备注" />
        <el-table-column prop="bg3res" label="血液酸碱度-检查结果" />
        <el-table-column prop="bg3unit" label="血液酸碱度-单位" />
        <el-table-column prop="bg3clsig" label="血液酸碱度-结果判定" />
        <el-table-column prop="bg3oth" label="血液酸碱度-备注" />
        <el-table-column v-if="checkPer(['admin','abgexam:edit','abgexam:del'])" label="操作" width="150px" align="center">
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
import crudAbgexam from '@/api/abgexam'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, bgyn: null, bgchkdat: null, bg1res: null, bg1unit: null, bg1clsig: null, bg1oth: null, bg2res: null, bg2unit: null, bg2clsig: null, bg2oth: null, bg3res: null, bg3unit: null, bg3clsig: null, bg3oth: null }
export default {
  name: 'Abgexam',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '血气分析', url: 'api/abgexam', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudAbgexam }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'abgexam:add'],
        edit: ['admin', 'abgexam:edit'],
        del: ['admin', 'abgexam:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'bgyn', display_name: '血气分析（是/否）' },
        { key: 'bgchkdat', display_name: '检查日期（年/月/日）' },
        { key: 'bg1res', display_name: '血氧饱和度-检查结果' },
        { key: 'bg1clsig', display_name: '血氧饱和度-结果判定' },
        { key: 'bg2res', display_name: '血氧分压-检查结果' },
        { key: 'bg2clsig', display_name: '血氧分压-结果判定' },
        { key: 'bg3res', display_name: '血液酸碱度-检查结果' },
        { key: 'bg3clsig', display_name: '血液酸碱度-结果判定' }
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
