package info.rixlabs.api;



import java.io.Serializable;

public class AuthenticationResponseDto implements Serializable {

	private static final long serialVersionUID = -6624726180748515507L;
	private String token;

	public AuthenticationResponseDto() {
		super();
	}

	public AuthenticationResponseDto(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
