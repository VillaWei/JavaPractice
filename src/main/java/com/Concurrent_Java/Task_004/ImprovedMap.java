package com.Concurrent_Java.Task_004;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.Concurrent_Java.ThreadSafe;

@ThreadSafe
class ImprovedMap<K, V> implements Map<K, V> {
    private final Map<K, V> map;
 
    public ImprovedMap(Map<K, V> map) {
        this.map = map;
    }
 
    public V put(K key, V value) {
        synchronized (map) {
            map.put(key, value);
        }
		return value;
    }
 
    public int size() {
        synchronized (map) {
            return map.size();
        }
    }

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
