package com.jdan.newsonline.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jdan.newsonline.domain.constants.Config;


/**
 * All rights Reserved, Designed By GeofferySun
 * 
 * @Title: SharedUtil.java
 * @Package sun.geoffery.libaray.utils
 * @Description:SharePreference存储配置信息的工具类 <p>
 *                                        注：可读取的数据类型有-
 *                                        <code>boolean、int、float、long、String.</code>
 *                                        </p>
 * @author: GeofferySun
 * @date: 2014-12-6 下午5:37:01
 * @version V1.0
 */
public class SharedUtil {
	/**
	 * <p>
	 * SharedPreferences类，它是一个轻量级的存储类，特别适合用于保存软件配置参数。
	 * SharedPreferences保存数据，其背后是用xml文件存放数据。 文件存放在/data/data/<package
	 * name>/shared_prefs目录下。
	 * 如果不通过创建Context访问其他应用的preference，可以以读取xml文件方式直接访问其他应用preference对应的xml文件，如：
	 * File xmlFile = new File(“/data/data/<package
	 * name>/shared_prefs/itcast.xml”);//<package name>应替换成应用的包名。
	 * 
	 * 与SQLite的比较:
	 * SharedPreferences对象与SQLite数据库相比，免去了创建数据库，创建表，写SQL语句等诸多操作，相对而言更加方便，简洁。
	 * 但是SharedPreferences也有其自身缺陷
	 * ，比如其职能存储boolean，int，float，long和String五种简单的数据类型，比如其无法进行条件查询等。
	 * 所以不论SharedPreferences的数据存储操作是如何简单
	 * ，它也只能是存储方式的一种补充，而无法完全替代如SQLite数据库这样的其他数据存储方式。
	 * </p>
	 */

	/** 使用SharedPreferences 来储存与读取数据 */
	private static SharedPreferences mShared = null;
	/**
	 * SharedPreferences的名称 程序中可以同时存在多个SharedPreferences数据， 根据这个名称就可以拿到对象
	 */
	private static String mDefaultFileName;
	private static Context mContext;
	private final String MAK = "android"; // AES加密的密钥

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @return
	 */
	public static SharedUtil getInstance(Context context) {
		mContext = context;
		synchronized (SharedUtil.class) {
			if (mShared == null) {
				synchronized (SharedUtil.class) {
					if (mShared == null) {
						// 拿到名称是SHARED_MAIN 的SharedPreferences对象
						mShared = context.getSharedPreferences(
								Config.SP_NAME, Context.MODE_PRIVATE);
					}
				}
			}
		}
		return new SharedUtil();
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static SharedUtil getInstance(Context context, String fileName) {
		if (mDefaultFileName == null
				|| !mDefaultFileName.trim().equals(fileName)) {
			mDefaultFileName = fileName;
			// 拿到名称是SHARED_MAIN 的SharedPreferences对象
			mShared = context.getSharedPreferences(fileName,
					Context.MODE_PRIVATE);// 私有方式存储,其他应用无法访问
		}
		return new SharedUtil();
	}

	/**
	 * 判断SharedPreferences是否包含特定key的数据
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		return mShared.contains(key);
	}

	/**
	 * 清空SharedPreferences里面的所有数据
	 * 
	 * @return
	 */
	public boolean clearAllSp() {
		Editor editor = mShared.edit();// 获取编辑器
		editor.clear();
		return editor.commit();
	}

	/**
	 * 删除SharedPreferences里指定的key对应的数据的项
	 * 
	 * @param key
	 * @return
	 */
	public boolean removeKey(String key) {
		Editor editor = mShared.edit();
		editor.remove(key);
		return editor.commit();
	}

	/**
	 * 保存对象
	 * 
	 * @param key
	 * @param obj
	 */
	public void putBean(String key, Object obj) {
		String objString = JsonUtils.INSTANCE.createJsonStr(obj);
		mShared.edit().putString(key, objString).commit();
	}

	/**
	 * 获取对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getBean(String key, Class<T> clazz) {
		String objString = mShared.getString(key, "");
		T parseJson;
		try {
			parseJson = (T) JsonUtils.INSTANCE.parseJson(objString, clazz);
		} catch (Exception e) {
			return null;
		}
		return parseJson;
	}

	/**
	 * 获取SharedPreferences数据里全部的key-value对
	 * 
	 * @return
	 */
	public Map<String, ?> getAllSharePreference() {
		return mShared.getAll();
	}
	

	/**
	 * 以key-value键值对保存String字符串型
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void putString(String key, String value) {
		mShared.edit().putString(key, value).commit();
	}

	/**
	 * 以String Key为索引获取String字符串型
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public String getString(String key, String defaultValue) {
		return mShared.getString(key, defaultValue);
	}

	/**
	 * 以key-value键值对保存Boolean类型
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void putBoolean(String key, boolean value) {
		mShared.edit().putBoolean(key, value).commit();
	}

	/**
	 * 以String Key为索引获取Boolean类型
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public boolean getBoolean(String key, boolean defaultValue) {
		return mShared.getBoolean(key, defaultValue);
	}

	/**
	 * 以key-value键值对保存Int整形
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void putInt(String key, int value) {
		mShared.edit().putInt(key, value).commit();
	}

	/**
	 * 以String Key为索引获取Int整形
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public int getInt(String key, int defaultValue) {
		return mShared.getInt(key, defaultValue);
	}

	/**
	 * 以key-value键值对保存浮点数Float
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void putFloat(String key, float value) {
		mShared.edit().putFloat(key, value).commit();
	}

	/**
	 * 以String Key为索引获取浮点数Float
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public float getFloat(String key, float defaultValue) {
		return mShared.getFloat(key, defaultValue);
	}

	/**
	 * 以key-value键值对保存长整形Long
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void putLong(String key, long value) {
		mShared.edit().putLong(key, value).commit();
	}

	/**
	 * 以String Key为索引获取长整形Long
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public long getLong(String key, long defaultValue) {
		return mShared.getLong(key, defaultValue);
	}

	/**
	 * 存储List<String>
	 * 
	 * @param key
	 *            List<String>对应的key
	 * @param strList
	 *            对应需要存储的List<String>
	 */
	public void putArrayList(String key, List<String> strList) {
		if (null == strList) {
			return;
		}
		// 保存之前先清理已经存在的数据，保证数据的唯一性
		removeStrList(key);
		int size = strList.size();
		putInt(key + "size", size);
		for (int i = 0; i < size; i++) {
			putString(key + i, strList.get(i));
		}
	}

	/**
	 * 清空List<String>所有数据
	 * 
	 * @param key
	 *            List<String>对应的key
	 */
	private void removeStrList(String key) {
		int size = getInt(key + "size", 0);
		if (0 == size) {
			return;
		}
		removeKey(key + "size");
		for (int i = 0; i < size; i++) {
			removeKey(key + i);
		}
	}

	// //////////////////// API-11 以上支持此方法 //////////////////////
	public Set<String> putStringSet(String key, Set<String> defaultValue) {
		return mShared.getStringSet(key, defaultValue);
	}

	public void setStringSet(String key, Set<String> value) {
		mShared.edit().putStringSet(key, value).commit();
	}

//	/**
//	 * 获取个人信息
//	 * @return
//	 */
//	public UserInfo getUserInfo() {
//		String objString = mShared.getString(Config.USERINFO, "");
//		UserInfo entity;
//		try {
//			entity = JsonUtils.parseJson(objString, UserInfo.class);
//		} catch (Exception e) {
//			return null;
//		}
//		return entity;
//	}
//
//	/**
//	 * 保存UserInfo数据
//	 *
//	 * @return
//	 */
//	public void putUserInfo(Object obj) {
//		String objString = JsonUtils.createJsonString(obj);
//		mShared.edit().putString(Config.USERINFO, objString).commit();
//	}

//	/**
//	 * 更新个人信息
//	 * @param userInfo
//	 * @param saveFile
//	 * @param name
//	 * @param phone
//	 * @param telPhone
//	 * @param weiXin
//	 * @param qq
//	 * @param email
//	 * @param remark
//	 */
//    public void updateUserInfo(UserInfo userInfo, String saveFile, String name, String phone, String telPhone, String weiXin, String qq, String email, String remark) {
//		if (!StringUtils
//				.isEmpty(telPhone)) {
//			userInfo.setUser_phone(telPhone);
//		}
//		if (!StringUtils
//				.isEmpty(phone)) {
//			userInfo.setUser_tel(phone);
//		}
//		if (!StringUtils
//				.isEmpty(qq)) {
//			userInfo.setUser_qq(qq);
//		}
//		if (!StringUtils
//				.isEmpty(weiXin)) {
//			userInfo.setUser_wx(weiXin);
//		}
//		if (!StringUtils
//				.isEmpty(remark)) {
//			userInfo.setUser_comment(remark);
//		}
//		if (!StringUtils
//				.isEmpty(email)) {
//			userInfo.setUser_em(email);
//		}
//		if (!StringUtils.isEmpty(saveFile)){
//			userInfo.setUser_img(saveFile);
//		}
//    }
//
//	public void updateUserInfo(UserInfo userInfo) {
//    	if (hasKey(Config.USERINFO)){
//			removeKey(Config.USERINFO);
//		}
//		putUserInfo(userInfo);
//	}

//	/**
//	 * 更新登陆信息
//	 *
//	 * @param bean
//	 */
//	public void updateLoginEntry(MineBean bean) {
//		Login_entity entity = getLoginEntity();
//		UserInfoBean info = bean.getUserInfo();
//		entity.setRole_id(info.getRole_id());
//		entity.setRemark(info.getRemark());
//		entity.setUser_unit(info.getUser_unit());
//		entity.setLogin_cont(info.getLogin_cont());
//		entity.setUser_group(info.getUser_group());
//		entity.setUser_pinyin(info.getUser_pinyin());
//		entity.setUser_state(info.getUser_state());
//		entity.setLast_login(info.getLast_login());
//		entity.setLogin_name(info.getLogin_name());
//		entity.setUser_initial(info.getUser_initial());
//		entity.setRole_name(info.getRole_name());
//		entity.setUser_point(info.getUser_point());
//		entity.setUser_qq(info.getUser_qq());
//		entity.setUser_wx(info.getUser_wx());
//		entity.setLeader_level(info.getLeader_level());
//		entity.setUser_alias(info.getUser_alias());
//		entity.setUser_tags(info.getUser_tags());
//		entity.setUser_img(info.getUser_img());
//		entity.setUser_tel(info.getUser_tel());
//		entity.setUser_nickname(info.getUser_nickname());
//		entity.setUser_pass(info.getUser_pass());
//		entity.setUser_name(info.getUser_name());
//		entity.setUser_phone(info.getUser_phone());
//		entity.setUser_em(info.getUser_em());
//		entity.setUser_comment(info.getUser_comment());
//		entity.setUser_id(info.getUser_id());
//		removeKey(Constant.USERINFO);
//		putLoginEntity(entity);
//	}

	// //////////////////// API-11 以上支持此方法 //////////////////////



}
