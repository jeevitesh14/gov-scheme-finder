import { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./Navbar";
import Dashboard from "./Dashboard";
import ChatWidget from "./ChatWidget";
import Login from "./Login";
import Register from "./Register";
import AdminDashboard from "./AdminDashboard";
import ApplicationsPage from "./ApplicationsPage";
import ProfilePage from "./ProfilePage";
import BookmarksPage from "./BookmarksPage";
import { bookmarkService } from "./api";
import "./index.css";

function App() {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem("user");
    try {
      return saved ? JSON.parse(saved) : null;
    } catch (e) {
      console.error("Failed to parse user from localStorage", e);
      localStorage.removeItem("user");
      return null;
    }
  });
  const [chatIsOpen, setChatIsOpen] = useState(false);
  const [bookmarks, setBookmarks] = useState([]);
  const [bookmarksLoading, setBookmarksLoading] = useState(false);

  const fetchBookmarks = async () => {
    if (!user) return;
    setBookmarksLoading(true);
    try {
      const response = await bookmarkService.getBookmarks();
      setBookmarks(response.data);
    } catch (err) {
      console.error("Failed to fetch bookmarks:", err);
    } finally {
      setBookmarksLoading(false);
    }
  };

  useEffect(() => {
    if (user) {
      fetchBookmarks();
    } else {
      setBookmarks([]);
    }
  }, [user]);

  const handleLogin = (userData) => {
    setUser(userData);
    localStorage.setItem("user", JSON.stringify(userData));
  };

  const handleLogout = () => {
    setUser(null);
    localStorage.removeItem("user");
  };


  const ProtectedRoute = ({ children }) => {
    if (!user) {
      return <Navigate to="/login" replace />;
    }
    return children;
  };

  const ProtectedAdminRoute = ({ children }) => {
    if (!user || user.role !== "ADMIN") {
      return <Navigate to="/" replace />;
    }
    return children;
  };

  return (
    <BrowserRouter>
      <div className="app-container">
        <Navbar 
          user={user} 
          onLogout={handleLogout} 
        />
        
        <Routes>
          <Route path="/login" element={user ? <Navigate to="/" replace /> : <Login onLogin={handleLogin} />} />
          <Route path="/register" element={user ? <Navigate to="/" replace /> : <Register />} />
          
          <Route 
            path="/" 
            element={
              <ProtectedRoute>
                <Dashboard 
                  onOpenChat={() => setChatIsOpen(true)} 
                />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="/admin" 
            element={
              <ProtectedAdminRoute>
                <AdminDashboard onLogout={handleLogout} />
              </ProtectedAdminRoute>
            } 
          />
          <Route 
            path="/applications" 
            element={
              <ProtectedRoute>
                <ApplicationsPage />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="/bookmarks" 
            element={
              <ProtectedRoute>
                <BookmarksPage 
                  bookmarks={bookmarks} 
                  onBookmark={fetchBookmarks} 
                />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="/profile" 
            element={
              <ProtectedRoute>
                <ProfilePage />
              </ProtectedRoute>
            } 
          />
        </Routes>

        {(!user || user.role !== "ADMIN") && (
          <ChatWidget 
            isOpen={chatIsOpen} 
            onClose={() => setChatIsOpen(false)} 
            onToggle={() => setChatIsOpen(!chatIsOpen)} 
          />
        )}
      </div>
    </BrowserRouter>
  );
}

export default App;
