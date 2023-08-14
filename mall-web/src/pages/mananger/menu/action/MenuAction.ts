export type Action = {
  actionId: string,
  menuId: string,
  actionName: string,
  actionCode: string,
  state: string
}

export type MenuAction = {
  actionId: string,
  menuId: string,
  actionName: string,
  actionCode: string,
  state: string,
  menuName: string,
  menuCnName: string
}