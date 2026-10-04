import { useState, useEffect } from "react";
import { getEducation } from "../services/api";
import "./Education.css";

function Education() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getEducation()
      .then(setItems)
      .catch(() => setError("Could not load education."))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading education...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="timeline">
      {items.map((e) => (
        <article className="timeline-item" key={e.id}>
          <span className="timeline-dot"></span>
          <div className="timeline-card">
            <div className="timeline-top">
              <h3>{e.institution}</h3>
              <span className="timeline-years">
                {e.startYear} – {e.endYear}
              </span>
            </div>
            <p className="timeline-degree">{e.degree}</p>
            <p className="timeline-score">{e.score}</p>
          </div>
        </article>
      ))}
    </div>
  );
}

export default Education;