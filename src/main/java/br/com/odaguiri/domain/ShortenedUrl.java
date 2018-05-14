package br.com.odaguiri.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shortened_url")
public class ShortenedUrl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "hash_url", nullable = false, unique = true)
    private String hashUrl;

    @NotNull
    @Column(name = "long_url", nullable = false, unique = true)
    private String longUrl;

    @NotNull
    @Column(name = "short_url", nullable = false, unique = true)
    private String shortUrl;
    
    @Transient
    @JsonIgnore
    private boolean exists = false;
    
    public ShortenedUrl(String longUrl, String hashUrl, String shortUrl) {
    	this.longUrl = longUrl;
    	this.hashUrl = hashUrl;
    	this.shortUrl = shortUrl;
    }
    
    public ShortenedUrl() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashUrl() {
		return hashUrl;
	}

	public void setHashUrl(String hashUrl) {
		this.hashUrl = hashUrl;
	}

	public String getLongUrl() {
        return longUrl;
    }

    public ShortenedUrl longUrl(String longUrl) {
        this.longUrl = longUrl;
        return this;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public ShortenedUrl shortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
        return this;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShortenedUrl shortenedUrl = (ShortenedUrl) o;
        if (shortenedUrl.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shortenedUrl.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShortenedUrl{" +
            "id=" + getId() +
            ", hashUrl='" + getHashUrl() + "'" +
            ", longUrl='" + getLongUrl() + "'" +
            ", shortUrl='" + getShortUrl() + "'" +
            "}";
    }
}
