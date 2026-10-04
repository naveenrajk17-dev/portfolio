import { useState } from "react";
import { sendContact } from "../services/api";
import "./Contact.css";

const EMPTY_FORM = { name: "", email: "", subject: "", message: "" };

function Contact({ email, location, github }) {
  const [form, setForm] = useState(EMPTY_FORM);
  const [status, setStatus] = useState("idle");
  const [feedback, setFeedback] = useState("");

  const handleChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!form.name.trim() || !form.email.trim() || !form.message.trim()) {
      setStatus("error");
      setFeedback("Please fill in your name, email and message.");
      return;
    }

    setStatus("sending");
    setFeedback("");

    try {
      await sendContact(form);
      setStatus("success");
      setFeedback("Thank you! Your message has been sent.");
      setForm(EMPTY_FORM);
    } catch {
      setStatus("error");
      setFeedback("Could not send the message. Please check your email address and try again.");
    }
  };

  return (
    <div className="contact">
      <section className="contact-info">
        <h3>Contact Info</h3>

        <div className="contact-row">
          <span className="contact-icon">✉</span>
          <div>
            <p className="contact-label">Mail Us</p>
            <a href={`mailto:${email}`}>{email}</a>
          </div>
        </div>

        <div className="contact-row">
          <span className="contact-icon">📍</span>
          <div>
            <p className="contact-label">Location</p>
            <p className="contact-value">{location}</p>
          </div>
        </div>

        <h3 className="contact-social-title">Social Info</h3>
        <a className="chip" href={github} target="_blank" rel="noreferrer">
          GitHub
        </a>
      </section>

      <section className="contact-form-card">
        <h3>Let's Work Together.</h3>

        <form onSubmit={handleSubmit} noValidate>
          <input
            type="text"
            name="name"
            placeholder="Name*"
            value={form.name}
            onChange={handleChange}
            maxLength={100}
          />
          <input
            type="email"
            name="email"
            placeholder="Email*"
            value={form.email}
            onChange={handleChange}
          />
          <input
            type="text"
            name="subject"
            placeholder="Subject"
            value={form.subject}
            onChange={handleChange}
            maxLength={150}
          />
          <textarea
            name="message"
            placeholder="Message*"
            rows={5}
            value={form.message}
            onChange={handleChange}
            maxLength={2000}
          ></textarea>

          <button
            type="submit"
            className="contact-submit"
            disabled={status === "sending"}
          >
            {status === "sending" ? "Sending..." : "Send Message"}
          </button>

          {feedback && (
            <p
              className={
                status === "success" ? "contact-feedback ok" : "contact-feedback bad"
              }
            >
              {feedback}
            </p>
          )}
        </form>
      </section>
    </div>
  );
}

export default Contact;