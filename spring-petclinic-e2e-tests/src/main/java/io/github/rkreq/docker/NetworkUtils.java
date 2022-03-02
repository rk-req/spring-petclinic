package io.github.rkreq.docker;

import com.google.common.base.Suppliers;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.function.Supplier;

public class NetworkUtils {

	public static NetworkUtils instance() {
		return InstanceHolder.instance;
	}

	private final Supplier<String> networkIp= Suppliers.memoize(this::getNetworkIpAddressImpl);

	public String getNetworkIpAddress() {
		return networkIp.get();
	}

	/**
	 * Returns ip address of preferable non-loopback interface. Source: https://stackoverflow.com/a/38342964
	 */
	private String getNetworkIpAddressImpl() {

		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			return socket.getLocalAddress().getHostAddress();
		} catch (UnknownHostException | SocketException e) {
			throw new RuntimeException(e);
		}
	}

	private interface InstanceHolder {

		NetworkUtils instance = new NetworkUtils();
	}
}
