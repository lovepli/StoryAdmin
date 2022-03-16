// 自定义枚举处理类和枚举定义
import { Enum } from '@/utils/Enum'
// ----------------- 系统相关状态枚举说明 ---------------------- //
/**
 * 枚举类
 */
export default {
  // 系统角色相关枚举(字段名、字段描述(前端展示)、字段值(后端交互))
  roleEnum: new Enum()
  // 定义用户默认登录角色-普通用户(无任何访问权限-初始化状态)
    .add('DEFAULT_ROLE', '登录角色-普通用户', 'DEFAULT_ROLE')
  /** 超级管理员 **/
    .add('SUPER_ADMIN', '超级管理员', 'SUPER_ADMIN')
  /** 系统维护管理员类别:用于维护系统管理相关模块数据 **/
    .add('SYS_ADMIN_BRANCH', '分行系统管理员', 'SYS_ADMIN_BRANCH')
    .add('SYS_ADMIN_SUB_BRANCH', '支行系统管理员', 'SYS_ADMIN_SUB_BRANCH')
    .add('SYS_ADMIN_DOT', '网点系统管理员', 'SYS_ADMIN_DOT')
  /** 管理员类别 **/
    .add('ADMIN_MANUAL_OUT_CALL', '人工外呼管理员', 'ADMIN_MANUAL_OUT_CALL')
    .add('ADMIN_ROBOT_OUT_CALL', '机器人外呼管理员', 'ADMIN_ROBOT_OUT_CALL')
    .add('ADMIN_CREDIT_CARD_DEPT_OUT_CALL', '信用卡部外呼管理员', 'ADMIN_CREDIT_CARD_DEPT_OUT_CALL')
  /** 质保类岗位 **/
  // .add('QA_IN_CALL_CHECKER', '呼入审核员', 'QA_IN_CALL_CHECKER')
  // .add('QA_OUT_CALL_CHECKER', '呼出审核员', 'QA_OUT_CALL_CHECKER')
  // .add('QA_QC', '质检员', 'QA_QC')
    .add('QA_IN_CALL_EXPERT', '呼入专家', 'QA_IN_CALL_EXPERT')
  /** 客服坐席岗位 **/
    .add('CALL_SEAT_IN_CALL', '呼入坐席', 'CALL_SEAT_IN_CALL')
    .add('CALL_SEAT_OUT_CALL', '呼出坐席', 'CALL_SEAT_OUT_CALL')
  /** 跟踪渠道相关(TRACK CHANNEL) **/
    .add('TC_RGWH', '企业微信经理', 'TC_RGWH')
    .add('TC_BRANCH_MANAGER', '支行网点管理人员', 'TC_BRANCH_MANAGER')
    .add('TC_BRANCH_CUST_MANAGER', '支行网点客户经理', 'TC_BRANCH_CUST_MANAGER')
  /** 机器人管理模块 */
    .add('ROBOT_BRANCH_MANAGER', '支行网点管理人员', 'ROBOT_BRANCH_MANAGER')
    .add('ROBOT_BRANCH_CUST_MANAGER', '支行网点客户经理', 'ROBOT_BRANCH_CUST_MANAGER'),

  // ---------- 企业微信管理相关枚举定义  --------------------------

  // 企业微信回访配置
  rvModeEnum: new Enum()
  // 运行中的流程状态相关(针对运行中的企业微信流程)
    .add('CALL_SEAT', '由关联的外呼坐席进行回访', '1')
    .add('CUSTOMER_SERVICE', '由指定的企业微信经理进行回访', '2')
    .add('RANDOM', '默认指定的随机分配列表', '3'),

  // 企业微信流程流转状态枚举说明
  wechatFlowStatusEnum: new Enum()
  // 运行中的流程状态相关(针对运行中的企业微信流程)
    .add('STATUS_INIT', '初始化', '-1')
    .add('STATUS_RV_ING', '回访中', '0')
    .add('STATUS_PRE_END', '待结单', '1')
  // 历史的流程状态相关(针对历史的企业微信流程)
    .add('STATUS_END_SYS', '系统自动结单', 'E1')
    .add('STATUS_END_BUS', '业务手动结单', 'E2'),

  // 流程处理标记
  pendingSignEnum: new Enum()
    .add('UNDONE', '未处理', '0')
    .add('DONE', '已处理', '1'),

  // 企业微信流程关联客户登记结果枚举
  registerResEnum: new Enum()
  // 系统自动处理状态相关
    .add('REGISTER_RES_INIT', '初始化(尚未标记)', '-1')
  // .add('REGISTER_RES_SYS_RV', '回访中', '0')
    .add('REGISTER_RES_SYS_SUCCESS', '系统标记-通过', 'S1')
  // 业务人工处理状态相关
    .add('REGISTER_RES_BUS_PRE_RV', '人工标记-待回访', 'B0')
    .add('REGISTER_RES_BUS_FAIL', '人工标记-不通过', 'B1')
    .add('REGISTER_RES_BUS_SUCCESS', '人工标记-通过', 'B2'),

  // 回访记录过程状态说明
  rvStatusEnum: new Enum()
  // 回访记录过程状态说明
    .add('RV_STATUS_PRE_FIRST_RV', '待第一次回访', '-1') // 待第一次回访（初始化状态）
    .add('RV_STATUS_PRE_SEC_RV', '待第二次回访', '1')
  // 回访记录结束状态说明
    .add('RV_STATUS_END_FORBID_SEC_RV', '禁止二次回访', 'E0')
    .add('RV_STATUS_END_SEC_RV', '二次回访结束', 'E1')
    .add('RV_STATUS_END_QYWX', '关联企业微信流程终止结束回访流程', 'E2'),

  // 回访结果枚举说明
  rvResEnum: new Enum()
    .add('RV_RES_INIT', '初始化(尚未操作)', '-1')
    .add('RV_RES_FAIL', '未接通', '0')
    .add('RV_RES_SUCCESS', '已接通', '1'),
  // 是否禁止二次外呼枚举说明
  isForbidSecEnum: new Enum()
    .add('IS_FORBID_SEC_NO', '否', '0')
    .add('IS_FORBID_SEC_YES', '是', '1'),

  // 客户登记状态：人工标记不通过枚举说明
  busPassEndReasonEnum: new Enum()
    .add('P001', '成功说明1', '成功说明1')
    .add('P002', '成功说明2', '成功说明2'),

  // 客户登记状态：人工标记通过枚举说明
  busRejectEndReasonEnum: new Enum()
    .add('R001', '客户拒绝', '客户拒绝')
    .add('R002', '搜不到微信', '搜不到微信')
    .add('R003', '实名信息有误', '实名信息有误')

}

// 使用方式：
//      // 按钮配置过滤操作:根据登录用户角色
// 获取登录用户信息
//   var loginUser = this.$store.state.loginUser ;
//   var loginUserRoleKey = loginUser.currentRoleKey ;
//   console.log('当前登录用户角色:',loginUserRoleKey);
//   if(this.$enums.roleEnum['ADMIN_ROBOT_OUT_CALL'].value === loginUserRoleKey){
// this.isBranchManage = true; // 支行网点管理员 控制显示分配按钮
// }
