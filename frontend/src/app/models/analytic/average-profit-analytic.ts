import { OrderTypeString } from "@models/order-type-string";

export interface AverageProfitAnalytic {
  forOrderTypes: {
    orderType: OrderTypeString,
    averageProfit: number
  }[]
}
