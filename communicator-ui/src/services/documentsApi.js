import { api } from "./api";

export const documentsApi = api.injectEndpoints({
  endpoints: (builder) => ({
    uploadDocument: builder.mutation({
      query: (formData) => ({
        url: "/documents/upload",
        method: "POST",
        body: formData,
      }),
    }),
    getDocuments: builder.query({
      query: () => "/documents",
    }),
  }),
});

export const {
  useUploadDocumentMutation,
  useGetDocumentsQuery,   // ✅ Ye hook ab available hai
} = documentsApi;
