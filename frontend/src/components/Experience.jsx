import { useState, useEffect } from "react";
import { getExperience } from "../services/api";
import "./Experience.css";

function Experience() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getExperience()
      .then(setItems)
      .catch(() => setError("Could not load experience."))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading experience...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="experience">
      {items.map((e) => (
        <article className="experience-card" key={e.id}>
          <div className="experience-top">
            <div>
              <h3>{e.company}</h3>
              <p className="experience-role">{e.role}</p>
            </div>
            <span className="experience-date">
              {e.startDate} – {e.endDate}
            </span>
          </div>
          <p>{e.description}</p>
        </article>
      ))}
    </div>
  );
}

export default Experience;