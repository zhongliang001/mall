import type { ExtractPropTypes } from 'vue'

const ztcStatistics={
  id:{
    type:String
  },
  prdId:{
    type: String
  },
  keyWord:{
    type:String
  },
  statDate:{
    type:String
  },
  potentialIdx: {
    type: Number
  },
  impressions: {
    type: Number
  },
  hits: {
    type: Number
  },
  cost: {
    type: Number
  },
  collections:{
    type: Number
  },
  addPurchases: {
    type: Number
  },
  shopCollections: {
    type: Number
  },
  payments: {
    type: Number
  },
  payAmt: {
    type: Number
  },
  keyState: {
    type: String
  },
  shopId: {
    type: String
  },
  createDate: {
    type: String
  },
  createdUserId: {
    type: String
  },
  updateDate: {
    type: String
  },
  updateUserId: {
    type: String
  }
}

export type ZtcStatistics = ExtractPropTypes<typeof ztcStatistics>