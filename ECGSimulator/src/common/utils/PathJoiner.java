package common.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PathJoiner {

	private static final String SLASH_UNIX_STYLE = "/";
	private static final String SLASH_WINDOWS_STYLE_ESCAPED = "\\\\";

	public static String join(String path1, Object... rest) {
		final String[] restStrings = Stream.of(rest).map(String::valueOf).toArray(String[]::new);
		final Path path = Paths.get(path1, restStrings);
		return path.toString().replaceAll(SLASH_WINDOWS_STYLE_ESCAPED, SLASH_UNIX_STYLE);
	}
}
