import { useState, useEffect } from "react";
import { getProjects } from "../services/api";
import "./Projects.css";

function Projects() {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getProjects()
      .then(setProjects)
      .catch(() => setError("Could not load projects."))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading projects...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="projects">
      {projects.map((p) => (
        <article className="project-card" key={p.id}>
          <h3>{p.title}</h3>
          <p className="project-subtitle">{p.subtitle}</p>
          <p>{p.description}</p>

          <div className="project-tech">
            {p.techStack.split(",").map((t) => (
              <span className="tech-tag" key={t}>
                {t.trim()}
              </span>
            ))}
          </div>

          <div className="project-links">
            {p.liveUrl && (
              <a className="chip chip-primary" href={p.liveUrl} target="_blank" rel="noreferrer">
                Live Demo
              </a>
            )}
            {p.githubUrl && (
              <a className="chip" href={p.githubUrl} target="_blank" rel="noreferrer">
                GitHub
              </a>
            )}
          </div>
        </article>
      ))}
    </div>
  );
}

export default Projects;