type Menu = {
  menuId: string
  menuCnName: string
  path: string
  menuName: string
  list: Array<Action>|undefined|any
  children: Array<Menu>
  comp: string
}

type Action = {
  actionId: string,
  menuId: string,
  actionName: string,
  actionCode: string,
  state: string
}

export type {Menu, Action}