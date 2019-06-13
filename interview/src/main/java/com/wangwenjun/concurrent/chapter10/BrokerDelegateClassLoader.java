package com.wangwenjun.concurrent.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class BrokerDelegateClassLoader extends ClassLoader {
	private final static Path DEFAULT_CLASS_DIR = Paths.get("target/","classes");

	private final Path classDir;


	public BrokerDelegateClassLoader() {
		super();
		this.classDir = DEFAULT_CLASS_DIR;
	}


	//允许通过参数传入类路径


	public BrokerDelegateClassLoader(String classDir) {
		super();
		this.classDir = Paths.get(classDir);
	}

	public BrokerDelegateClassLoader(ClassLoader parent, Path classDir) {
		super(parent);
		this.classDir = classDir;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes = this.readClassBytes(name);
		if (null == classBytes || classBytes.length == 0) {
			throw new ClassNotFoundException("Can not load the class" + name);
		}
		return this.defineClass(name, classBytes, 0, classBytes.length);

	}

	private byte[] readClassBytes(String name) throws ClassNotFoundException {
		String classPath = name.replace(".", "/");
		Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
		System.out.println(classFullPath.toString());
		if (!classFullPath.toFile().exists()) {
			throw new ClassNotFoundException("The class " + name + " not fund");
		}
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Files.copy(classFullPath, baos);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new ClassNotFoundException("load the class " + name + " occur error.", e);
		}
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		synchronized (getClassLoadingLock(name)) {
			Class<?> klass = findLoadedClass(name);
			if (klass == null) {
				if (name.startsWith("java.") || name.startsWith("javax.")) {
					try {
						klass = getSystemClassLoader().loadClass(name);
					} catch (Exception e) {
						throw e;
					}

				} else {
					try {
						klass = this.findClass(name);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (klass == null) {
						if (getParent() != null) {
							klass = getParent().loadClass(name);
						} else {
							klass = getSystemClassLoader().loadClass(name);
						}
					}
				}
			}
			if (null == klass) {
				throw new ClassNotFoundException("The class " + name + " not found.");
			}
			if (resolve) {
				resolveClass(klass);
			}
			return klass;
		}
	}

	@Override
	public String toString() {
		return "My ClassLoader";
	}
}
