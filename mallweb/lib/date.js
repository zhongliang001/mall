/* eslint no-extend-native: ["error", { "exceptions": ["Date"] }] */
Date.prototype.format = function (fmt) {
  const opt = {
    // 年份
    'y+': this.getFullYear(),
    // 月份
    'M+': this.getMonth() + 1,
    // 日
    'd+': this.getDate(),
    // 12小时
    'h+': this.getHours() / 12 < 0 ? this.getHours() : this.getHours() % 12,
    // 24小时
    'H+': this.getHours(),
    // 分
    'm+': this.getMinutes(),
    // 秒
    's+': this.getSeconds(),
    // 季度
    'q+': Math.floor((this.getMonth() + 3) / 3),
    // 毫秒
    S: this.getMilliseconds()
  }
  for (const k in opt) {
    debugger
    const ret = new RegExp('(' + k + ')').exec(fmt)
    if (ret) {
      if (/(y+)/.test(k)) {
        fmt = fmt.replace(ret[1], opt[k].toFixed().substring(4 - ret[1].length))
      } else {
        fmt = fmt.replace(ret[1], (ret[1].length === 1) ? (opt[k]) : (opt[k].toFixed().padStart(ret[1].length, '0')))
      }
    }
  }
  return fmt
}
