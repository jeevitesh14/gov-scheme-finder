import { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./Navbar";
import Dashboard from "./Dashboard";
import ChatWidget from "./ChatWidget";
import BookmarksPage from "./BookmarksPage";
import Login from "./Login";
import Register from "./Register";
import AdminDashboard from "./AdminDashboard";
import { bookmarkService } from "./api";
import { translations } from "./translations";
import "./index.css";

function App() {
  const [language, setLanguage] = useState(() => localStorage.getItem("language") || "English");
  const t = translations[language] || translations["English"];

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
  const [bookmarks, setBookmarks] = useState([]);
  const [chatIsOpen, setChatIsOpen] = useState(false);
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

  useEffect(() => {
    localStorage.setItem("language", language);
  }, [language]);

  const handleLogin = (userData) => {
    setUser(userData);
    localStorage.setItem("user", JSON.stringify(userData));
  };

  const handleLogout = () => {
    setUser(null);
    localStorage.removeItem("user");
    setBookmarks([]);
  };

  const handleBookmark = async (scheme) => {
    const isBookmarked = bookmarks.some(b => b.id === scheme.id);
    try {
      if (isBookmarked) {
        await bookmarkService.removeBookmark(scheme.id);
        setBookmarks(prev => prev.filter(b => b.id !== scheme.id));
      } else {
        await bookmarkService.addBookmark(scheme.id);
        setBookmarks(prev => [...prev, scheme]);
      }
    } catch (err) {
      console.error("Bookmark operation failed:", err);
      // alert("Failed to update bookmark. Please try again.");
    }
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
          language={language} 
          onLanguageChange={setLanguage} 
          t={t}
        />
        
        <Routes>
          <Route path="/login" element={user ? <Navigate to="/" replace /> : <Login onLogin={handleLogin} />} />
          <Route path="/register" element={user ? <Navigate to="/" replace /> : <Register />} />
          
          <Route 
            path="/" 
            element={
              <ProtectedRoute>
                <Dashboard 
                  bookmarks={bookmarks} 
                  onBookmark={handleBookmark} 
                  onOpenChat={() => setChatIsOpen(true)} 
                  t={t}
                />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="/bookmarks" 
            element={
              <ProtectedRoute>
                <BookmarksPage 
                  bookmarks={bookmarks} 
                  onBookmark={handleBookmark} 
                  t={t}
                />
              </ProtectedRoute>
            } 
          />
          <Route 
            path="/admin" 
            element={
              <ProtectedAdminRoute>
                <AdminDashboard onLogout={handleLogout} t={t} />
              </ProtectedAdminRoute>
            } 
          />
        </Routes>

        {(!user || user.role !== "ADMIN") && (
          <ChatWidget 
            isOpen={chatIsOpen} 
            onClose={() => setChatIsOpen(false)} 
            onToggle={() => setChatIsOpen(!chatIsOpen)} 
            t={t}
          />
        )}
      </div>
    </BrowserRouter>
  );
}

export default App;
