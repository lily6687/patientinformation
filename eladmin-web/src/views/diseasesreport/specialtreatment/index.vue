<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">是否接收特殊治疗</label>
        <el-input v-model="query.styn" clearable placeholder="是否接收特殊治疗" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">机械通气（是/否）</label>
        <el-input v-model="query.mvyn" clearable placeholder="机械通气（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">ECMO（是/否）</label>
        <el-input v-model="query.emyn" clearable placeholder="ECMO（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">高流量鼻导管氧（是/否）</label>
        <el-input v-model="query.hfncoyn" clearable placeholder="高流量鼻导管氧（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">无创通气（是/否）</label>
        <el-input v-model="query.nonivyn" clearable placeholder="无创通气（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">持续性肾脏替代疗法（CRRT）（是/否）</label>
        <el-input v-model="query.crrtyn" clearable placeholder="持续性肾脏替代疗法（CRRT）（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">康复者血浆治疗（是/否）</label>
        <el-input v-model="query.rptyn" clearable placeholder="康复者血浆治疗（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血液净化治疗（是/否）</label>
        <el-input v-model="query.bptyn" clearable placeholder="血液净化治疗（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="是否接收特殊治疗">
            <el-radio v-model="form.styn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="机械通气（是/否）">
            <el-radio v-model="form.mvyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="开始日期">
            <el-date-picker v-model="form.mvstdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期">
            <el-date-picker v-model="form.mvendat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="详细治疗信息">
            <el-input v-model="form.stoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="ECMO（是/否）">
            <el-radio v-model="form.emyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="开始日期.1">
            <el-date-picker v-model="form.emstdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期.1">
            <el-date-picker v-model="form.emendat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="详细治疗信息.1">
            <el-input v-model="form.emoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="高流量鼻导管氧（是/否）">
            <el-radio v-model="form.hfncoyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="开始日期.2">
            <el-date-picker v-model="form.hfstdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期.2">
            <el-date-picker v-model="form.hfendat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="详细治疗信息.2">
            <el-input v-model="form.hfncooth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="无创通气（是/否）">
            <el-radio v-model="form.nonivyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="开始日期.3">
            <el-date-picker v-model="form.nosatdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期.3">
            <el-date-picker v-model="form.noendat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="详细治疗信息.3">
            <el-input v-model="form.nonivoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="持续性肾脏替代疗法（CRRT）（是/否）">
            <el-radio v-model="form.crrtyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="开始日期.4">
            <el-date-picker v-model="form.crstdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期.4">
            <el-date-picker v-model="form.crendat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="详细治疗信息.4">
            <el-input v-model="form.crrtoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="康复者血浆治疗（是/否）">
            <el-radio v-model="form.rptyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="开始日期.5">
            <el-date-picker v-model="form.rpstdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期.5">
            <el-date-picker v-model="form.rpendat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="详细治疗信息.5">
            <el-input v-model="form.rptoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血液净化治疗（是/否）">
            <el-radio v-model="form.bptyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="开始日期.6">
            <el-date-picker v-model="form.bpstdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期.6">
            <el-date-picker v-model="form.bpendat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="详细治疗信息.6">
            <el-input v-model="form.bptoth" style="width: 370px;" />
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
        <el-table-column prop="styn" label="是否接收特殊治疗">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.styn] }}
          </template>
        </el-table-column>
        <el-table-column prop="mvyn" label="机械通气（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.mvyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="mvstdat" label="开始日期" />
        <el-table-column prop="mvendat" label="结束日期" />
        <el-table-column prop="stoth" label="详细治疗信息" />
        <el-table-column prop="emyn" label="ECMO（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.emyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="emstdat" label="开始日期.1" />
        <el-table-column prop="emendat" label="结束日期.1" />
        <el-table-column prop="emoth" label="详细治疗信息.1" />
        <el-table-column prop="hfncoyn" label="高流量鼻导管氧（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.hfncoyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="hfstdat" label="开始日期.2" />
        <el-table-column prop="hfendat" label="结束日期.2" />
        <el-table-column prop="hfncooth" label="详细治疗信息.2" />
        <el-table-column prop="nonivyn" label="无创通气（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.nonivyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="nosatdat" label="开始日期.3" />
        <el-table-column prop="noendat" label="结束日期.3" />
        <el-table-column prop="nonivoth" label="详细治疗信息.3" />
        <el-table-column prop="crrtyn" label="持续性肾脏替代疗法（CRRT）（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.crrtyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="crstdat" label="开始日期.4" />
        <el-table-column prop="crendat" label="结束日期.4" />
        <el-table-column prop="crrtoth" label="详细治疗信息.4" />
        <el-table-column prop="rptyn" label="康复者血浆治疗（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.rptyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="rpstdat" label="开始日期.5" />
        <el-table-column prop="rpendat" label="结束日期.5" />
        <el-table-column prop="rptoth" label="详细治疗信息.5" />
        <el-table-column prop="bptyn" label="血液净化治疗（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.bptyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="bpstdat" label="开始日期.6" />
        <el-table-column prop="bpendat" label="结束日期.6" />
        <el-table-column prop="bptoth" label="详细治疗信息.6" />
        <el-table-column v-if="checkPer(['admin','specialtreatment:edit','specialtreatment:del'])" label="操作" width="150px" align="center">
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
import crudSpecialtreatment from '@/api/specialtreatment'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, styn: null, mvyn: null, mvstdat: null, mvendat: null, stoth: null, emyn: null, emstdat: null, emendat: null, emoth: null, hfncoyn: null, hfstdat: null, hfendat: null, hfncooth: null, nonivyn: null, nosatdat: null, noendat: null, nonivoth: null, crrtyn: null, crstdat: null, crendat: null, crrtoth: null, rptyn: null, rpstdat: null, rpendat: null, rptoth: null, bptyn: null, bpstdat: null, bpendat: null, bptoth: null }
export default {
  name: 'Specialtreatment',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '治疗情况_特殊治疗', url: 'api/specialtreatment', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudSpecialtreatment }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'specialtreatment:add'],
        edit: ['admin', 'specialtreatment:edit'],
        del: ['admin', 'specialtreatment:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'styn', display_name: '是否接收特殊治疗' },
        { key: 'mvyn', display_name: '机械通气（是/否）' },
        { key: 'emyn', display_name: 'ECMO（是/否）' },
        { key: 'hfncoyn', display_name: '高流量鼻导管氧（是/否）' },
        { key: 'nonivyn', display_name: '无创通气（是/否）' },
        { key: 'crrtyn', display_name: '持续性肾脏替代疗法（CRRT）（是/否）' },
        { key: 'rptyn', display_name: '康复者血浆治疗（是/否）' },
        { key: 'bptyn', display_name: '血液净化治疗（是/否）' }
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
