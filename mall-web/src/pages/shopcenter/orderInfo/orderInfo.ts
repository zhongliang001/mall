type OrderInfo = {
  orderId?: string
  buyerId?: string
  platOrderId?: string
  area?: string
  vendorOrderId?: string
  purchaseTime?: string
  state?: string
}

type OrderDetail = {
  prdId?: string
  prdName?: string
  skuId?: string
  skuName?: string
  sellPrice?: number
  sellPostage?: number
  purchasePostage?: number
  sales?: number
  vendorPrice?: number
}

export type { OrderInfo, OrderDetail }
