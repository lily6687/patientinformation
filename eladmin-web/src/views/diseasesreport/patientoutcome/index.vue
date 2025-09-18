<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">治疗结局（是/否）</label>
        <el-input v-model="query.trtoutyn" clearable placeholder="治疗结局（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">痊愈（是/否）</label>
        <el-input v-model="query.curedyn" clearable placeholder="痊愈（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">病情加重（是/否）</label>
        <el-input v-model="query.worseyn" clearable placeholder="病情加重（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">转院治疗（是/否）</label>
        <el-input v-model="query.trftrtyn" clearable placeholder="转院治疗（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">转入ICU（是/否）</label>
        <el-input v-model="query.icuyn" clearable placeholder="转入ICU（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">呼吸衰竭（是/否）</label>
        <el-input v-model="query.rpfyn" clearable placeholder="呼吸衰竭（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">死亡（是/否）</label>
        <el-input v-model="query.deadyn" clearable placeholder="死亡（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">休克（是/否）</label>
        <el-input v-model="query.shokyn" clearable placeholder="休克（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">是否为核酸检测（是/否）</label>
        <el-input v-model="query.natyn" clearable placeholder="是否为核酸检测（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="访视">
            <el-input v-model="form.visit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="患者编号">
            <el-input v-model="form.subjid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="治疗结局（是/否）">
            <el-radio v-model="form.trtoutyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）">
            <el-date-picker v-model="form.trchkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="痊愈（是/否）">
            <el-radio v-model="form.curedyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）">
            <el-date-picker v-model="form.cureddat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.curedoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="病情加重（是/否）">
            <el-radio v-model="form.worseyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）.1">
            <el-date-picker v-model="form.worsedat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.1">
            <el-input v-model="form.worseoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="转院治疗（是/否）">
            <el-radio v-model="form.trftrtyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）.2">
            <el-date-picker v-model="form.trfdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.2">
            <el-input v-model="form.trfoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="转入ICU（是/否）">
            <el-radio v-model="form.icuyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）.3">
            <el-date-picker v-model="form.icudat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.3">
            <el-input v-model="form.icuoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="呼吸衰竭（是/否）">
            <el-radio v-model="form.rpfyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）.4">
            <el-date-picker v-model="form.rpfdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.4">
            <el-input v-model="form.rpfoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="死亡（是/否）">
            <el-radio v-model="form.deadyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）.5">
            <el-date-picker v-model="form.deaddat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.5">
            <el-input v-model="form.deadoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="休克（是/否）">
            <el-radio v-model="form.shokyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）.6">
            <el-date-picker v-model="form.shokdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.6">
            <el-input v-model="form.shokoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="其他（是/否）">
            <el-radio v-model="form.othyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="日期（年/月/日）.7">
            <el-date-picker v-model="form.othdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.7">
            <el-input v-model="form.othoth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="病原学检测类型">
            <el-input v-model="form.tstype" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="病原学检测结果（新型冠状病毒感染、甲型流感、人感染高致病性禽流感、登革热及百日咳）">
            <el-input v-model="form.tsres" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="是否为核酸检测（是/否）">
            <el-radio v-model="form.natyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="核酸CT值">
            <el-input v-model="form.natct" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.8">
            <el-input v-model="form.nat3oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="病原学转阴时间">
            <el-date-picker v-model="form.pnegdt" type="datetime" style="width: 370px;" />
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
        <el-table-column prop="visit" label="访视" />
        <el-table-column prop="subjid" label="患者编号" />
        <el-table-column prop="trtoutyn" label="治疗结局（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.trtoutyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="trchkdat" label="检查日期（年/月/日）" />
        <el-table-column prop="curedyn" label="痊愈（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.curedyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="cureddat" label="日期（年/月/日）" />
        <el-table-column prop="curedoth" label="备注" />
        <el-table-column prop="worseyn" label="病情加重（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.worseyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="worsedat" label="日期（年/月/日）.1" />
        <el-table-column prop="worseoth" label="备注.1" />
        <el-table-column prop="trftrtyn" label="转院治疗（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.trftrtyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="trfdat" label="日期（年/月/日）.2" />
        <el-table-column prop="trfoth" label="备注.2" />
        <el-table-column prop="icuyn" label="转入ICU（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.icuyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="icudat" label="日期（年/月/日）.3" />
        <el-table-column prop="icuoth" label="备注.3" />
        <el-table-column prop="rpfyn" label="呼吸衰竭（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.rpfyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="rpfdat" label="日期（年/月/日）.4" />
        <el-table-column prop="rpfoth" label="备注.4" />
        <el-table-column prop="deadyn" label="死亡（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.deadyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="deaddat" label="日期（年/月/日）.5" />
        <el-table-column prop="deadoth" label="备注.5" />
        <el-table-column prop="shokyn" label="休克（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.shokyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="shokdat" label="日期（年/月/日）.6" />
        <el-table-column prop="shokoth" label="备注.6" />
        <el-table-column prop="othyn" label="其他（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.othyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="othdat" label="日期（年/月/日）.7" />
        <el-table-column prop="othoth" label="备注.7" />
        <el-table-column prop="tstype" label="病原学检测类型" />
        <el-table-column prop="tsres" label="病原学检测结果（新型冠状病毒感染、甲型流感、人感染高致病性禽流感、登革热及百日咳）" />
        <el-table-column prop="natyn" label="是否为核酸检测（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.natyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="natct" label="核酸CT值" />
        <el-table-column prop="nat3oth" label="备注.8" />
        <el-table-column prop="pnegdt" label="病原学转阴时间" />
        <el-table-column v-if="checkPer(['admin','patientoutcome:edit','patientoutcome:del'])" label="操作" width="150px" align="center">
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
import crudPatientoutcome from '@/api/patientoutcome'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { visit: null, columnId: null, subjid: null, trtoutyn: null, trchkdat: null, curedyn: null, cureddat: null, curedoth: null, worseyn: null, worsedat: null, worseoth: null, trftrtyn: null, trfdat: null, trfoth: null, icuyn: null, icudat: null, icuoth: null, rpfyn: null, rpfdat: null, rpfoth: null, deadyn: null, deaddat: null, deadoth: null, shokyn: null, shokdat: null, shokoth: null, othyn: null, othdat: null, othoth: null, tstype: null, tsres: null, natyn: null, natct: null, nat3oth: null, pnegdt: null }
export default {
  name: 'Patientoutcome',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '治疗结局', url: 'api/patientoutcome', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudPatientoutcome }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'patientoutcome:add'],
        edit: ['admin', 'patientoutcome:edit'],
        del: ['admin', 'patientoutcome:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'trtoutyn', display_name: '治疗结局（是/否）' },
        { key: 'curedyn', display_name: '痊愈（是/否）' },
        { key: 'worseyn', display_name: '病情加重（是/否）' },
        { key: 'trftrtyn', display_name: '转院治疗（是/否）' },
        { key: 'icuyn', display_name: '转入ICU（是/否）' },
        { key: 'rpfyn', display_name: '呼吸衰竭（是/否）' },
        { key: 'deadyn', display_name: '死亡（是/否）' },
        { key: 'shokyn', display_name: '休克（是/否）' },
        { key: 'natyn', display_name: '是否为核酸检测（是/否）' }
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
