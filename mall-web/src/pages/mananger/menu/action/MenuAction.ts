export type Action = {
  actionId: string
  menuId: string
  actionName: string
  actionCode: string
  state: string
}

export type MenuAction = {
  actionId: string
  menuId: string
  actionName: string
  actionCode: string
  state: string
  menuName: string
  menuCnName: string
}

export type MenuActionList = {
  menuId: string
  menuCnName: string
  list: Array<MenuAction>
  children: Array<MenuActionList>
}
