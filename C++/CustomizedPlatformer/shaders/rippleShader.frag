// attributes from vertShader.vert
#version 110

varying vec4 vColor;
varying vec2 vTexCoord;

// uniforms
uniform sampler2D uTexture;
uniform float uTime;

void main() {
	vec2 texCoord = vTexCoord;
	float coef = sin(gl_FragCoord.y * 0.1 + 1.0 * uTime);
	texCoord.x +=  coef * 0.015;
	texCoord.y +=  coef * 0.001;
	gl_FragColor = vColor * texture2D(uTexture, texCoord);
	

}
