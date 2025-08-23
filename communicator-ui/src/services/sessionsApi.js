// src/services/sessionsApi.js
import { api } from "./api";

export const sessionsApi = api.injectEndpoints({
  endpoints: (builder) => ({
    startSession: builder.mutation({
      query: (mode = "chat") => ({
        url: `/sessions/start`,
        method: "POST",
        body: { mode }, // "chat" | "pdf"
      }),
    }),
    endSession: builder.mutation({
      query: (sessionId) => ({
        url: `/sessions/${sessionId}/end`,
        method: "POST",
      }),
    }),
    getSessionMessages: builder.query({
      query: (sessionId) => `/sessions/${sessionId}/messages`,
    }),
    listSessions: builder.query({
        query: () => `/sessions`,
    }),

  }),
  overrideExisting: false,
});

export const {
  useStartSessionMutation,
  useEndSessionMutation,
  useGetSessionMessagesQuery,
  useListSessionsQuery,
} = sessionsApi;
