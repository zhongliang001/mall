/**
 * 将数字转换成用逗号隔开，小数点后保留两位
 * @param data
 */
export const formatCurrency = (data: number) => {
  let ds = data.toString()
  const dss = ds.split('.')
  const intData = dss[0]
  let currency: string = ''
  if (intData.length < 3) {
    currency = intData
  } else {
    const first: number = intData.length % 3
    let pice: number = intData.length / 3
    if (first > 0) {
      currency = intData.substring(0, first)
      if (pice > 0) {
        currency += ','
      }
    }
    let index = 0
    while (pice > 0) {
      currency += intData.substring(first + index * 3, first + (index + 1) * 3)
      pice--
      index++
      if (pice >= 1) {
        currency += ','
      }
    }
  }
  if (dss.length === 1) {
    return currency + '.00'
  } else {
    const dg = dss[1]
    if (dg.length === 1) {
      return currency + '.' + dg + '0'
    } else if (dg.length === 2) {
      return currency + '.' + dg
    } else {
      const rd = dg.charAt(2)
      if (Number(rd) < 5) {
        return currency + '.' + dg.substring(0, 2)
      } else {
        return currency + '.' + (Number(dg.substring(0, 2)) + 1)
      }
    }
  }
}
