package spring.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

public class ByteArrayDataSource implements DataSource {
	
	private byte[] data;
	private String type;
	private String name;
	
	public ByteArrayDataSource(InputStream is, String type, String name)throws IOException {
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		for (int count; (count = is.read(buf)) != -1;)
			os.write(buf, 0, count);
		data = os.toByteArray();
		this.type = type;
		this.name = name;
		
	}
	
	public ByteArrayDataSource(byte[] data, String type, String name) {
		this.data = data;
		this.type = type;
		this.name = name;
	}

	@Override
	public String getContentType() {
		return type;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		if (data == null)
			throw new IOException();
			return new ByteArrayInputStream(data);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		throw new IOException();
	}

}
