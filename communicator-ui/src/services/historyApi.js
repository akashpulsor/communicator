import { api } from './api'

export const historyApi = api.injectEndpoints({
  endpoints: (build) => ({
    historyByUser: build.query({
      query: () => '/history', // set this to your endpoint (e.g., /sessions?with_last_message=1)
      providesTags: ['Session']
    })
  })
})

export const { useHistoryByUserQuery } = historyApi
