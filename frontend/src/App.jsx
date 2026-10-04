import { useState, useEffect } from "react";
import Header from "./components/Header";
import About from "./components/About";
import Tabs from "./components/Tabs";
import Projects from "./components/Projects";
import Experience from "./components/Experience";
import Skills from "./components/Skills";
import Education from "./components/Education";
import Certificates from "./components/Certificates";
import Contact from "./components/Contact";
import "./App.css";

const TABS = [
  "Projects",
  "Experience",
  "Skills",
  "Education",
  "Certificates",
  "Contact",
];

const PROFILE = {
  name: "Naveen Raj K",
  role: "Java Backend Developer",
  email: "naveenrajk315@gmail.com",
  github: "https://github.com/naveenrajk17-dev",
  location: "Chennai, Tamil Nadu",
  status: "Fresher",
  about:
    "Aspiring Java Backend Developer with a strong foundation in Java, Spring Boot, MySQL and REST APIs. Seeking an opportunity to build scalable applications and contribute to a dynamic software development team.",
};

function App() {
  const [theme, setTheme] = useState("light");
  const [activeTab, setActiveTab] = useState("Projects");

  useEffect(() => {
    document.documentElement.setAttribute("data-theme", theme);
  }, [theme]);

  const toggleTheme = () => {
    setTheme(theme === "light" ? "dark" : "light");
  };

  const renderTab = () => {
    switch (activeTab) {
      case "Projects":
        return <Projects />;
      case "Experience":
        return <Experience />;
      case "Skills":
        return <Skills />;
      case "Education":
        return <Education />;
      case "Certificates":
        return <Certificates />;
      case "Contact":
        return (
          <Contact
            email={PROFILE.email}
            location={PROFILE.location}
            github={PROFILE.github}
          />
        );
      default:
        return null;
    }
  };

  return (
    <div className="container">
      <Header
        name={PROFILE.name}
        role={PROFILE.role}
        email={PROFILE.email}
        github={PROFILE.github}
        theme={theme}
        onToggleTheme={toggleTheme}
      />

      <div className="layout">
        <About
          text={PROFILE.about}
          location={PROFILE.location}
          status={PROFILE.status}
        />

        <main>
          <Tabs tabs={TABS} active={activeTab} onChange={setActiveTab} />
          {renderTab()}
        </main>
      </div>
    </div>
  );
}

export default App;