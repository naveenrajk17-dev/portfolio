import { useState, useEffect } from "react";
import { getCertificates } from "../services/api";
import "./Certificates.css";

function Certificates() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [selected, setSelected] = useState(null);

  useEffect(() => {
    getCertificates()
      .then(setItems)
      .catch(() => setError("Could not load certificates."))
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => {
    const onKey = (event) => {
      if (event.key === "Escape") setSelected(null);
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, []);

  if (loading) return <p>Loading certificates...</p>;
  if (error) return <p>{error}</p>;

  return (
    <>
      <div className="cert-grid">
        {items.map((c) => (
          <article
            className="cert-card"
            key={c.id}
            onClick={() => c.imageUrl && setSelected(c)}
          >
            <div className="cert-preview">
              {c.imageUrl ? (
                <iframe
                  src={`${c.imageUrl}#toolbar=0&navpanes=0&scrollbar=0&view=Fit`}
                  title={c.title}
                  tabIndex={-1}
                ></iframe>
              ) : (
                <span className="cert-nopreview">No preview</span>
              )}
              <div className="cert-click-layer"></div>
            </div>
            <div className="cert-info">
              <p className="cert-issuer">{c.issuer}</p>
              <h3>{c.title}</h3>
              <p className="cert-date">{c.issueDate}</p>
            </div>
          </article>
        ))}
      </div>

      {selected && (
        <div className="cert-modal" onClick={() => setSelected(null)}>
          <div className="cert-modal-box" onClick={(e) => e.stopPropagation()}>
            <div className="cert-modal-top">
              <h3>{selected.title}</h3>
              <div className="cert-modal-actions">
                {selected.verifyUrl && (
                  <a
                    className="chip"
                    href={selected.verifyUrl}
                    target="_blank"
                    rel="noreferrer"
                  >
                    Verify
                  </a>
                )}
                <a
                  className="chip"
                  href={selected.imageUrl}
                  target="_blank"
                  rel="noreferrer"
                >
                  Open in new tab
                </a>
                <button className="chip" onClick={() => setSelected(null)}>
                  ✕ Close
                </button>
              </div>
            </div>
            <iframe
              className="cert-modal-frame"
              src={`${selected.imageUrl}#toolbar=0&navpanes=0&view=Fit`}
              title={selected.title}
            ></iframe>
          </div>
        </div>
      )}
    </>
  );
}

export default Certificates;