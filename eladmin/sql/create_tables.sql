
use diseasesreporting;

SET FOREIGN_KEY_CHECKS = 0;

-- Sheet: 人口学资料
DROP TABLE IF EXISTS `peopleinformation`;
CREATE TABLE peopleinformation  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    NAME VARCHAR(64) COMMENT '姓名',
    SUBJID VARCHAR(64) UNIQUE NOT NULL COMMENT '患者编号',
    SEX CHAR(2) COMMENT '性别（男/女）',
    BRTHDAT DATETIME COMMENT '出生日期（年/月/日）',
    NATIONALID VARCHAR(64) COMMENT '证件号码',
    TEL VARCHAR(64) COMMENT '电话号码',
    ADDR VARCHAR(64) COMMENT '地址',
    HOSPITAL VARCHAR(64) COMMENT '医院名称',
    AGE INT COMMENT '年龄',
    WGT FLOAT COMMENT '体重（kg）',
    HGT FLOAT COMMENT '身高（cm）',
  PRIMARY KEY (`column_id`) USING BTREE,
  KEY (SUBJID)
);


-- Sheet: 病史及过敏史
DROP TABLE IF EXISTS `historyallergy`;
CREATE TABLE historyallergy (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    DIAG VARCHAR(256) COMMENT '诊断',
    MHYN bit(1)  COMMENT '既往病史（是/否）',
    MHNAM VARCHAR(256) COMMENT '症状/疾病名称',
    AHYN bit(1)  COMMENT '过敏史（是/否）',
    AHALRGN VARCHAR(256) COMMENT '过敏原',
    CM1YN bit(1)  COMMENT '合并疾病（是/否）',
    CM1TRT VARCHAR(256) COMMENT '合并症名称',
    MHSMOKYN bit(1)  COMMENT '从不吸烟/已经戒烟/现在吸烟',
    MHQSMDUR INT COMMENT '戒烟_____年',
    MHSMKDUR INT COMMENT '吸烟_____年',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 发病史
DROP TABLE IF EXISTS `hosphistory`;
CREATE TABLE hosphistory  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    NASTDAT DATETIME COMMENT '发病日期（年/月/日）',
    NADOCDAT DATETIME COMMENT '就诊日期（年/月/日）',
    NAHOSDAT DATETIME COMMENT '入院日期（年/月/日）',
    DISCYN bit(1)  COMMENT '出院记录（是/否）',
    DISCDAT DATETIME COMMENT '出院日期（年/月/日）',
    CC VARCHAR(256) COMMENT '主诉',
    HPI VARCHAR(256) COMMENT '现病史',
    TRTDESC VARCHAR(256) COMMENT '诊疗过程描述',
    DISCSUPP VARCHAR(256) COMMENT '出院情况',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 呼吸道症状
DROP TABLE IF EXISTS `respsymptoms`;
CREATE TABLE respsymptoms  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    RESPTSYN bit(1)  COMMENT '呼吸道症状（是/否）',
    RECHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    COUGHYN bit(1)  COMMENT '咳嗽（是/否）',
    DRYCOUYN bit(1)  COMMENT '干咳（是/否）',
    FATGYN bit(1)  COMMENT '乏力（是/否）',
    STUFNSYN bit(1)  COMMENT '鼻塞 （是/否）',
    RUNNSYN bit(1)  COMMENT '流涕（是/否）',
    ANRXYN bit(1)  COMMENT '厌食 （是/否）',
    DIARHYN bit(1)  COMMENT '腹泻 （是/否）',
    ASTMYN bit(1)  COMMENT '气喘（是/否）',
    CHILYN bit(1)  COMMENT '畏寒（是/否）',
    SOTHRYN bit(1)  COMMENT '咽痛（是/否）',
    BRCHKDAT DATETIME COMMENT '检查日期（年/月/日）.1',
    BRETHFRQ INTEGER COMMENT '呼吸（次/分）',
    TEMPYN bit(1)  COMMENT '体温（是/否）',
    TECHKDAT DATETIME COMMENT '检查日期（年/月/日）.2',
    TEMP FLOAT  COMMENT '体温（℃）',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 心电图
DROP TABLE IF EXISTS `ecgexam`;
CREATE TABLE ecgexam (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    EGYN bit(1)  COMMENT '心电图（是/否）',
    EGCHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    EGRES VARCHAR(256) COMMENT '检查结果（正常/异常）',
    EGOTH VARCHAR(256) COMMENT '备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 影像学检查
DROP TABLE IF EXISTS `imagingexam`;
CREATE TABLE imagingexam  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    MICHKYN bit(1)  COMMENT '影像学检查（是/否）',
    MITYPE VARCHAR(256) COMMENT '检查类型',
    MICHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    MIRES VARCHAR(256) COMMENT '检查结果（正常/异常）',
    MIOTH VARCHAR(256) COMMENT '备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 血常规
DROP TABLE IF EXISTS `cbcexam`;
CREATE TABLE cbcexam  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    BLYN bit(1)  COMMENT '血常规（是/否）',
    BLCHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    BL1RES FLOAT COMMENT '中性粒细胞计数-检查结果',
    BL1UNIT VARCHAR(256) COMMENT '中性粒细胞计数-单位',
    BL1CLSIG VARCHAR(256) COMMENT '中性粒细胞计数-结果判定',
    BL1OTH VARCHAR(256) COMMENT '中性粒细胞计数备注',
    BL2RES FLOAT COMMENT '淋巴细胞计数-检查结果',
    BL2UNIT VARCHAR(256) COMMENT '淋巴细胞计数-单位',
    BL2CLSIG VARCHAR(256) COMMENT '淋巴细胞计数-结果判定',
    BL2OTH VARCHAR(256) COMMENT '淋巴细胞计数-备注',
    BL3RES FLOAT COMMENT '红细胞计数-检查结果',
    BL3UNIT VARCHAR(256) COMMENT '红细胞计数-单位',
    BL3CLSIG VARCHAR(256) COMMENT '红细胞计数-结果判定',
    BL3OTH VARCHAR(256) COMMENT '红细胞计数-备注',
    BL4RES FLOAT COMMENT '血红蛋白-检查结果',
    BL4UNIT VARCHAR(256) COMMENT '血红蛋白-单位',
    BL4CLSIG VARCHAR(256) COMMENT '血红蛋白-结果判定',
    BL4OTH VARCHAR(256) COMMENT '备注',
    BL5RES FLOAT COMMENT '红细胞压积-检查结果',
    BL5UNIT VARCHAR(256) COMMENT '红细胞压积-单位',
    BL5CLSIG VARCHAR(256) COMMENT '红细胞压积-结果判定',
    BL5OTH VARCHAR(256) COMMENT '备注.1',
    BL6RES FLOAT COMMENT '白细胞计数-检查结果',
    BL6UNIT VARCHAR(256) COMMENT '白细胞计数-单位',
    BL6CLSIG VARCHAR(256) COMMENT '白细胞计数-结果判定',
    BL6OTH VARCHAR(256) COMMENT '白细胞计数-备注',
    BL7RES FLOAT COMMENT '血小板计数-检查结果',
    BL7UNIT VARCHAR(256) COMMENT '血小板计数-单位',
    BL7CLSIG VARCHAR(256) COMMENT '血小板计数-结果判定',
    BL7OTH VARCHAR(256) COMMENT '血小板计数-备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 尿常规
DROP TABLE IF EXISTS `urinalysis`;
CREATE TABLE urinalysis  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    URYN bit(1)  COMMENT '尿常规（是/否）',
    URCHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    UR1RES VARCHAR(256) COMMENT '尿白细胞-检查结果',
    UR1UNIT VARCHAR(256) COMMENT '尿白细胞-单位',
    UR1CLSIG VARCHAR(256) COMMENT '尿白细胞-结果判定',
    UR1OTH VARCHAR(256) COMMENT '尿白细胞-备注',
    UR2RES VARCHAR(256) COMMENT '尿红细胞-检查结果',
    UR2UNIT VARCHAR(256) COMMENT '尿红细胞-单位',
    UR2CLSIG VARCHAR(256) COMMENT '尿红细胞-结果判定',
    UR2OTH VARCHAR(256) COMMENT '尿红细胞-备注',
    UR3RES VARCHAR(256) COMMENT '尿糖-检查结果',
    UR3UNIT VARCHAR(256) COMMENT '尿糖-单位',
    UR3CLSIG VARCHAR(256) COMMENT '尿糖-结果判定',
    UR3OTH VARCHAR(256) COMMENT '尿糖-备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 血生化
DROP TABLE IF EXISTS `metapanel`;
CREATE TABLE metapanel  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    BCYN bit(1)  COMMENT '血生化 （是/否）',
    BCCHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    BC1RES FLOAT COMMENT '谷草转氨酶(AST) -检查结果',
    BLC1UNIT VARCHAR(256) COMMENT '谷草转氨酶(AST) -单位',
    BC1CLSIG VARCHAR(256) COMMENT '谷草转氨酶(AST) -结果判定',
    BC1OTH VARCHAR(256) COMMENT '谷草转氨酶(AST) -备注',
    BC2RES FLOAT COMMENT '谷丙转氨酶（ALT）-检查结果',
    BC2UNIT VARCHAR(256) COMMENT '谷丙转氨酶（ALT）-单位',
    BC2CLSIG VARCHAR(256) COMMENT '谷丙转氨酶（ALT）-结果判定',
    BC2OTH VARCHAR(256) COMMENT '谷丙转氨酶（ALT）-备注',
    BC3RES FLOAT COMMENT '肌酸激酶（CK）-检查结果',
    BC3UNIT VARCHAR(256) COMMENT '肌酸激酶（CK）单位',
    BC3CLSIG VARCHAR(256) COMMENT '肌酸激酶（CK）-结果判定',
    BC3OTH VARCHAR(256) COMMENT '肌酸激酶（CK）-备注',
    BC4RES FLOAT COMMENT '肌酸激酶同工酶（CKMB）-检查结果',
    BC4UNIT VARCHAR(256) COMMENT '肌酸激酶同工酶（CKMB）-单位',
    BC4CLSIG VARCHAR(256) COMMENT '肌酸激酶同工酶（CKMB）-结果判定',
    BC4OTH VARCHAR(256) COMMENT '肌酸激酶同工酶（CKMB）-备注',
    BC5RES FLOAT COMMENT '碱性磷酸酶（ALP）-检查结果',
    BC5UNIT VARCHAR(256) COMMENT '碱性磷酸酶（ALP）-单位',
    BC5CLSIG VARCHAR(256) COMMENT '碱性磷酸酶（ALP）-结果判定',
    BC5OTH VARCHAR(256) COMMENT '碱性磷酸酶（ALP）-备注',
    BC6RES FLOAT COMMENT '乳酸脱氢酶（LDH）-检查结果',
    BC6UNIT VARCHAR(256) COMMENT '乳酸脱氢酶（LDH）-单位',
    BC6CLSIG VARCHAR(256) COMMENT '乳酸脱氢酶（LDH）-结果判定',
    BC6OTH VARCHAR(256) COMMENT '乳酸脱氢酶（LDH）-备注',
    BC7RES FLOAT COMMENT '尿素氮（UREA）-检查结果',
    BC7UNIT VARCHAR(256) COMMENT '尿素氮（UREA）-单位',
    BC7CLSIG VARCHAR(256) COMMENT '尿素氮（UREA）-结果判定',
    BC7OTH VARCHAR(256) COMMENT '尿素氮（UREA）-备注',
    BC8RES FLOAT COMMENT '血肌酐（CREA）-检查结果',
    BC8UNIT VARCHAR(256) COMMENT '血肌酐（CREA）-单位',
    BC8CLSIG VARCHAR(256) COMMENT '血肌酐（CREA）-结果判定',
    BC8OTH VARCHAR(256) COMMENT '血肌酐（CREA）-备注',
    BC9RES FLOAT COMMENT 'Ca2+检查结果',
    BC9UNIT VARCHAR(256) COMMENT 'Ca2+单位',
    BC9CLSIG VARCHAR(256) COMMENT 'Ca2+结果判定',
    BC9OTH VARCHAR(256) COMMENT 'Ca2+备注',
    BC10RES FLOAT COMMENT 'Na+检查结果',
    BC10UNI VARCHAR(256) COMMENT 'Na+单位',
    BC10CLSI VARCHAR(256) COMMENT 'Na+结果判定',
    BC10REM VARCHAR(256) COMMENT 'Na+备注',
    BC11RES FLOAT COMMENT 'Cl-检查结果',
    BC11UNI VARCHAR(256) COMMENT 'Cl-单位',
    BC11CLSI VARCHAR(256) COMMENT 'Cl-结果判定',
    BC11REM VARCHAR(256) COMMENT 'Cl-备注',
    BC12RES FLOAT COMMENT 'K+检查结果',
    BC12UNI VARCHAR(256) COMMENT 'K+单位',
    BC12CLSI VARCHAR(256) COMMENT 'K+结果判定',
    BC12REM VARCHAR(256) COMMENT 'K+备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 免疫学指标
DROP TABLE IF EXISTS `immutests`;
CREATE TABLE immutests  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    IMYN bit(1)  COMMENT '免疫学指标（是/否）',
    IM1DAT DATETIME COMMENT 'C-反应蛋白-检查日期（年/月/日）',
    IM1RES FLOAT COMMENT 'C-反应蛋白-检查结果',
    IM1UNIT VARCHAR(256) COMMENT 'C-反应蛋白-单位',
    IM1CLSIG VARCHAR(256) COMMENT 'C-反应蛋白-结果判定',
    IM1OTH VARCHAR(256) COMMENT 'C-反应蛋白-备注',
    IM2DAT DATETIME COMMENT '血沉-检查日期（年/月/日）',
    IM2RES FLOAT COMMENT '血沉-检查结果',
    IM2UNIT VARCHAR(256) COMMENT '血沉-单位',
    IM2CLSIG VARCHAR(256) COMMENT '血沉-结果判定',
    IM2OTH VARCHAR(256) COMMENT '血沉-备注',
    IM3DAT DATETIME COMMENT '降钙素原-检查日期（年/月/日）',
    IM3RES FLOAT COMMENT '降钙素原-检查结果',
    IM3UNIT VARCHAR(256) COMMENT '降钙素原-单位',
    IM3CLSIG VARCHAR(256) COMMENT '降钙素原-结果判定',
    IM3OTH VARCHAR(256) COMMENT '降钙素原-备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 血气分析
DROP TABLE IF EXISTS `abgexam`;
CREATE TABLE abgexam (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    BGYN bit(1)  COMMENT '血气分析（是/否）',
    BGCHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    BG1RES FLOAT COMMENT '血氧饱和度-检查结果',
    BG1UNIT VARCHAR(256) COMMENT '血氧饱和度-单位',
    BG1CLSIG VARCHAR(256) COMMENT '血氧饱和度-结果判定',
    BG1OTH VARCHAR(256) COMMENT '血氧饱和度-备注',
    BG2RES FLOAT COMMENT '血氧分压-检查结果',
    BG2UNIT VARCHAR(256) COMMENT '血氧分压-单位',
    BG2CLSIG VARCHAR(256) COMMENT '血氧分压-结果判定',
    BG2OTH VARCHAR(256) COMMENT '血氧分压-备注',
    BG3RES FLOAT COMMENT '血液酸碱度-检查结果',
    BG3UNIT VARCHAR(256) COMMENT '血液酸碱度-单位',
    BG3CLSIG VARCHAR(256) COMMENT '血液酸碱度-结果判定',
    BG3OTH VARCHAR(256) COMMENT '血液酸碱度-备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 治疗情况_其他药物
DROP TABLE IF EXISTS `treatmentothermedication`;
CREATE TABLE treatmentothermedication  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    CM2YN bit(1)  COMMENT '是否使用其他药物（是/否）',
    CM2CAT VARCHAR(256) COMMENT '西药药物类型',
    CM2FEB VARCHAR(256) COMMENT '退烧药',
    CM2ANVI VARCHAR(256) COMMENT '抗病毒药',
    CM2ANBIO VARCHAR(256) COMMENT '抗生素',
    CE2REFLU VARCHAR(256) COMMENT '常规补液治疗',
    CM2HORMO VARCHAR(256) COMMENT '激素',
    CE2OTHER VARCHAR(256) COMMENT '其他药物',
    CM2ODESC VARCHAR(256) COMMENT '请描述',
    CM2OTH VARCHAR(256) COMMENT '备注',
    CM3TRT VARCHAR(256) COMMENT '中医药名称',
    CM3OTH VARCHAR(256) COMMENT '其他_请注明：',
    CMCHYN bit(1)  COMMENT '是否有更改治疗方案',
    CMCHDAT DATETIME COMMENT '是_更改时间：_____',
    CMCHOTH VARCHAR(256) COMMENT '是_请描述更改后治疗方案：',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);


-- Sheet: 治疗情况_特殊治疗
DROP TABLE IF EXISTS `specialtreatment`;
CREATE TABLE specialtreatment  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    STYN bit(1)  COMMENT '是否接收特殊治疗',
    MVYN bit(1)  COMMENT '机械通气（是/否）',
    MVSTDAT DATETIME COMMENT '开始日期',
    MVENDAT DATETIME COMMENT '结束日期',
    STOTH VARCHAR(256) COMMENT '详细治疗信息',
    EMYN bit(1)  COMMENT 'ECMO（是/否）',
    EMSTDAT DATETIME COMMENT '开始日期.1',
    EMENDAT DATETIME COMMENT '结束日期.1',
    EMOTH VARCHAR(256) COMMENT '详细治疗信息.1',
    HFNCOYN bit(1)  COMMENT '高流量鼻导管氧（是/否）',
    HFSTDAT DATETIME COMMENT '开始日期.2',
    HFENDAT DATETIME COMMENT '结束日期.2',
    HFNCOOTH VARCHAR(256) COMMENT '详细治疗信息.2',
    NONIVYN bit(1)  COMMENT '无创通气（是/否）',
    NOSATDAT DATETIME COMMENT '开始日期.3',
    NOENDAT DATETIME COMMENT '结束日期.3',
    NONIVOTH VARCHAR(256) COMMENT '详细治疗信息.3',
    CRRTYN bit(1)  COMMENT '持续性肾脏替代疗法（CRRT）（是/否）',
    CRSTDAT DATETIME COMMENT '开始日期.4',
    CRENDAT DATETIME COMMENT '结束日期.4',
    CRRTOTH VARCHAR(256) COMMENT '详细治疗信息.4',
    RPTYN bit(1)  COMMENT '康复者血浆治疗（是/否）',
    RPSTDAT DATETIME COMMENT '开始日期.5',
    RPENDAT DATETIME COMMENT '结束日期.5',
    RPTOTH VARCHAR(256) COMMENT '详细治疗信息.5',
    BPTYN bit(1)  COMMENT '血液净化治疗（是/否）',
    BPSTDAT DATETIME COMMENT '开始日期.6',
    BPENDAT DATETIME COMMENT '结束日期.6',
    BPTOTH VARCHAR(256) COMMENT '详细治疗信息.6',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);

-- Sheet: 治疗结局
DROP TABLE IF EXISTS `patientoutcome`;
CREATE TABLE patientoutcome  (
    VISIT VARCHAR(256) COMMENT '访视',
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    TRTOUTYN bit(1)  COMMENT '治疗结局（是/否）',
    TRCHKDAT DATETIME COMMENT '检查日期（年/月/日）',
    CUREDYN bit(1)  COMMENT '痊愈（是/否）',
    CUREDDAT DATETIME COMMENT '日期（年/月/日）',
    CUREDOTH VARCHAR(256) COMMENT '备注',
    WORSEYN bit(1)  COMMENT '病情加重（是/否）',
    WORSEDAT DATETIME COMMENT '日期（年/月/日）.1',
    WORSEOTH VARCHAR(256) COMMENT '备注.1',
    TRFTRTYN bit(1)  COMMENT '转院治疗（是/否）',
    TRFDAT DATETIME COMMENT '日期（年/月/日）.2',
    TRFOTH VARCHAR(256) COMMENT '备注.2',
    ICUYN bit(1)  COMMENT '转入ICU（是/否）',
    ICUDAT DATETIME COMMENT '日期（年/月/日）.3',
    ICUOTH VARCHAR(256) COMMENT '备注.3',
    RPFYN bit(1)  COMMENT '呼吸衰竭（是/否）',
    RPFDAT DATETIME COMMENT '日期（年/月/日）.4',
    RPFOTH VARCHAR(256) COMMENT '备注.4',
    DEADYN bit(1)  COMMENT '死亡（是/否）',
    DEADDAT DATETIME COMMENT '日期（年/月/日）.5',
    DEADOTH VARCHAR(256) COMMENT '备注.5',
    SHOKYN bit(1)  COMMENT '休克（是/否）',
    SHOKDAT DATETIME COMMENT '日期（年/月/日）.6',
    SHOKOTH VARCHAR(256) COMMENT '备注.6',
    OTHYN bit(1)  COMMENT '其他（是/否）',
    OTHDAT DATETIME COMMENT '日期（年/月/日）.7',
    OTHOTH VARCHAR(256) COMMENT '备注.7',
    TSTYPE VARCHAR(256) COMMENT '病原学检测类型',
    TSRES VARCHAR(256) COMMENT '病原学检测结果（新型冠状病毒感染、甲型流感、人感染高致病性禽流感、登革热及百日咳）',
    NATYN bit(1)  COMMENT '是否为核酸检测（是/否）',
    NATCT FLOAT COMMENT '核酸CT值',
    NAT3OTH VARCHAR(256) COMMENT '备注.8',
    PNEGDT VARCHAR(256) COMMENT '病原学转阴时间',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);

-- Sheet: 合并用药
DROP TABLE IF EXISTS `combinedmedication`;
CREATE TABLE combinedmedication  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    SUBJID VARCHAR(64) COMMENT '患者编号',
    CM3YN bit(1)  COMMENT '合并用药（是/否）',
    CM3TRT VARCHAR(256) COMMENT '药物名称',
    CM3STDAT DATETIME COMMENT '开始日期',
    CM3ENDAT DATETIME COMMENT '结束日期',
    CM3ORD VARCHAR(256) COMMENT '用药目的',
    CM3DOSE VARCHAR(256) COMMENT '剂量',
    CM3ROUTE VARCHAR(256) COMMENT '给药方式',
    CM3OTH VARCHAR(256) COMMENT '备注',
  PRIMARY KEY (`column_id`) USING BTREE,
  INDEX idx_subjid (SUBJID),
  FOREIGN KEY (SUBJID) REFERENCES peopleinformation(SUBJID)
);

SET FOREIGN_KEY_CHECKS = 1;

