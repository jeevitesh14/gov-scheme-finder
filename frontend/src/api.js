import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:9090",
});

// Request Interceptor to attach token
api.interceptors.request.use(
  (config) => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.token) {
      config.headers.Authorization = `Bearer ${user.token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response Interceptor to handle auto-logout on 401
api.interceptors.response.use(
  (response) => {
    // Extract data if it follows ApiResponse structure
    if (response.data && response.data.success !== undefined) {
      if (!response.data.success) {
        return Promise.reject(response.data);
      }
      return response.data; // Return only the ApiResponse object or its data
    }
    return response;
  },
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("user");
      // Use window.location.href or a navigate function from props if possible
      if (window.location.pathname !== "/login") {
        window.location.href = "/login";
      }
    }
    const message = error.response?.data?.message || error.message || "An unexpected error occurred";
    return Promise.reject({ message, ...error.response?.data });
  }
);

export const authService = {
  login: (credentials) => api.post("/api/auth/login", credentials),
  register: (userData) => api.post("/api/auth/register", userData),
};

export const schemeService = {
  getSchemes: (params) => api.get("/api/schemes", { params }),
  createScheme: (data) => api.post("/api/schemes", data),
  updateScheme: (id, data) => api.put(`/api/schemes/${id}`, data),
  deleteScheme: (id) => api.delete(`/api/schemes/${id}`),
};

export const bookmarkService = {
  getBookmarks: () => api.get("/api/bookmarks"),
  addBookmark: (schemeId) => api.post(`/api/bookmarks/${schemeId}`),
  removeBookmark: (schemeId) => api.delete(`/api/bookmarks/${schemeId}`),
};

export default api;
