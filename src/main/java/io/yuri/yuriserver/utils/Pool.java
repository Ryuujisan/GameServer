package io.yuri.yuriserver.utils;

import java.util.*;
import java.util.stream.Stream;

public class Pool<T extends Poolable> implements Collection<T> {

    private final List<T> list;
    private final TreeSet<Integer> freeKeys;
    private int size;

    public Pool() {
        list = new ArrayList<>();
        freeKeys = new TreeSet<>();
        size = 0;
    }

    public int register(T obj) {
        int id = list.indexOf(obj);
        if(id >= 0) {
            return id;
        }

        if(!freeKeys.isEmpty()) {
            id = freeKeys.pollFirst();
            obj.setId(id);
            list.set(id, obj);
            size++;
            return id;
        }

        list.add(obj);
        size++;
        obj.setId(list.size() - 1);
        return list.size() - 1;
    }

    public void unregister(T obj) {
        int id = list.indexOf(obj);
        if (id < 0) {
            throw new NoSuchElementException();
        }

        size--;
        list.set(id, null);
        freeKeys.add(id);
    }

    public <C> Iterable <C> byType(Class<C> myClass) {
        return () -> list.stream()
                .filter(t -> t != null)
                .filter(myClass::isInstance)
                .map(myClass::cast)
                .iterator();
    }

    public <C> Stream<C> byTypeStream(Class<C> myClass) {
        return list.stream()
                .filter(t -> t != null)
                .filter(myClass::isInstance)
                .map(myClass::cast);
    }

    public boolean exists(int id) {
        return  id < list.size() && list.get(id) != null;
    }

    public T get(int id) {
        return list.get(id);
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return list
                .isEmpty()
                ||
                list
                .stream()
                .allMatch((t -> t == null));
    }

    @Override
    public boolean contains(Object o) {
        return list
                .stream()
                .anyMatch(t -> t == o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.stream()
                .filter(t -> t != null)
                .iterator();
    }

    @Override
    public Object[] toArray() {
        return list
                .stream()
                .toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        register(t);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        if(contains(o)) {
            unregister((T) o);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list
                .containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        list.clear();
        size = 0;
    }
}
