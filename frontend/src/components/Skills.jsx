import { useState, useEffect } from "react";
import { getSkills } from "../services/api";
import "./Skills.css";

function Skills() {
  const [skills, setSkills] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getSkills()
      .then(setSkills)
      .catch(() => setError("Could not load skills."))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading skills...</p>;
  if (error) return <p>{error}</p>;

  const grouped = skills.reduce((groups, skill) => {
    if (!groups[skill.category]) {
      groups[skill.category] = [];
    }
    groups[skill.category].push(skill);
    return groups;
  }, {});

  return (
    <div className="skills">
      {Object.entries(grouped).map(([category, list]) => (
        <section className="skill-group" key={category}>
          <h3>{category}</h3>
          <div className="skill-list">
            {list.map((s) => (
              <span className="skill-tag" key={s.id}>
                {s.name}
              </span>
            ))}
          </div>
        </section>
      ))}
    </div>
  );
}

export default Skills;