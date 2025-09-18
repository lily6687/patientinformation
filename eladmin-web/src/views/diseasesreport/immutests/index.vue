<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">免疫学指标（是/否）</label>
        <el-input v-model="query.imyn" clearable placeholder="免疫学指标（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="免疫学指标（是/否）">
            <el-radio v-model="form.imyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="C-反应蛋白-检查日期（年/月/日）">
            <el-date-picker v-model="form.im1dat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="C-反应蛋白-检查结果">
            <el-input v-model="form.im1res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="C-反应蛋白-单位">
            <el-input v-model="form.im1unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="C-反应蛋白-结果判定">
            <el-input v-model="form.im1clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="C-反应蛋白-备注">
            <el-input v-model="form.im1oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血沉-检查日期（年/月/日）">
            <el-date-picker v-model="form.im2dat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血沉-检查结果">
            <el-input v-model="form.im2res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血沉-单位">
            <el-input v-model="form.im2unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血沉-结果判定">
            <el-input v-model="form.im2clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血沉-备注">
            <el-input v-model="form.im2oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="降钙素原-检查日期（年/月/日）">
            <el-date-picker v-model="form.im3dat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="降钙素原-检查结果">
            <el-input v-model="form.im3res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="降钙素原-单位">
            <el-input v-model="form.im3unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="降钙素原-结果判定">
            <el-input v-model="form.im3clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="降钙素原-备注">
            <el-input v-model="form.im3oth" style="width: 370px;" />
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
        <el-table-column prop="imyn" label="免疫学指标（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.imyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="im1dat" label="C-反应蛋白-检查日期（年/月/日）" />
        <el-table-column prop="im1res" label="C-反应蛋白-检查结果" />
        <el-table-column prop="im1unit" label="C-反应蛋白-单位" />
        <el-table-column prop="im1clsig" label="C-反应蛋白-结果判定" />
        <el-table-column prop="im1oth" label="C-反应蛋白-备注" />
        <el-table-column prop="im2dat" label="血沉-检查日期（年/月/日）" />
        <el-table-column prop="im2res" label="血沉-检查结果" />
        <el-table-column prop="im2unit" label="血沉-单位" />
        <el-table-column prop="im2clsig" label="血沉-结果判定" />
        <el-table-column prop="im2oth" label="血沉-备注" />
        <el-table-column prop="im3dat" label="降钙素原-检查日期（年/月/日）" />
        <el-table-column prop="im3res" label="降钙素原-检查结果" />
        <el-table-column prop="im3unit" label="降钙素原-单位" />
        <el-table-column prop="im3clsig" label="降钙素原-结果判定" />
        <el-table-column prop="im3oth" label="降钙素原-备注" />
        <el-table-column v-if="checkPer(['admin','immutests:edit','immutests:del'])" label="操作" width="150px" align="center">
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
import crudImmutests from '@/api/immutests'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, imyn: null, im1dat: null, im1res: null, im1unit: null, im1clsig: null, im1oth: null, im2dat: null, im2res: null, im2unit: null, im2clsig: null, im2oth: null, im3dat: null, im3res: null, im3unit: null, im3clsig: null, im3oth: null }
export default {
  name: 'Immutests',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '免疫学指标', url: 'api/immutests', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudImmutests }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'immutests:add'],
        edit: ['admin', 'immutests:edit'],
        del: ['admin', 'immutests:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'imyn', display_name: '免疫学指标（是/否）' }
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
