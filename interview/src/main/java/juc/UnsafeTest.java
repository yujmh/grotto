package juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		Field field = Unsafe.class.getDeclaredField("theUnsafe");
		field.setAccessible(true);
		Unsafe unsafe = (Unsafe) field.get(null);
		System.out.println(unsafe);

		System.out.println(unsafe.arrayIndexScale(int[].class));
		System.out.println(unsafe.arrayIndexScale(long[].class));
		System.out.println(unsafe.arrayIndexScale(double[].class));

		System.out.println(31 - Integer.numberOfLeadingZeros(unsafe.arrayIndexScale(int[].class)));
	}
}
