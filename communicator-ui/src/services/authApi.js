import { api } from "./api";

export const authApi = api.injectEndpoints({
  endpoints: (builder) => ({
    login: builder.mutation({
      query: (credentials) => ({
        url: "/auth/signin",
        method: "POST",
        body: credentials,
      }),
      async onQueryStarted(arg, { queryFulfilled }) {
        try {
          const { data } = await queryFulfilled
            console.log(data)
            if (data?.access_token) {
                localStorage.setItem("token", data.access_token)   // 👈 use access_token
            }
            if (data?.refresh_token) {
                localStorage.setItem("refreshToken", data.refresh_token) // optional
            }
        } catch {
          // ignore errors
        }
      }
    }),
    register: builder.mutation({
      query: (data) => ({
        url: "/auth/signup",
        method: "POST",
        body: data,
      }),
    }),
  }),
  overrideExisting: false,
});

export const { useLoginMutation, useRegisterMutation } = authApi;
