import store from '../store'

/**
 * 权限细粒度控制按钮显示，其中有一个问题，就是不添加v-if="hasPerm('sysmgr.user.save')" 也会显示按钮，只有权限编码出错才按钮不会显示
 * v-if="hasPerm('sysmgr.user.save')"
 * @param {} permission 
 */
export function hasPermission(permission) {
  const myPermissions = store.getters.permissions;
 // console.log("权限值数组=》"+JSON.stringify(myPermissions))
  console.log("权限值=》"+myPermissions.indexOf(permission))
  return myPermissions.indexOf(permission) > -1;
}
