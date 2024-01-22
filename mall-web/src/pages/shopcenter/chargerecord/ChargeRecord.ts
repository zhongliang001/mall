import type { ExtractPropTypes } from 'vue'

const chargeRecord = {
  id: {
    type: String
  },
  chargeDate: {
    type: String
  },
  chargeAmt: {
    type: Number
  }
}

export type ChargeRecord = ExtractPropTypes<typeof chargeRecord>
