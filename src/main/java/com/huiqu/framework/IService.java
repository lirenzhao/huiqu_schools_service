package com.huiqu.framework;

import java.util.Map;

public interface IService {

	Map<String, Object> exec(Map<String, String> req);
}
