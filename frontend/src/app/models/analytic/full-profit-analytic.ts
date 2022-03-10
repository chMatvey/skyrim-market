import { OrderTypeString } from "@models/order-type-string";

export interface FullProfitAnalytic {
  forOrderTypes: {
    orderType: OrderTypeString,
    fullProfit: number
  }[]
}
