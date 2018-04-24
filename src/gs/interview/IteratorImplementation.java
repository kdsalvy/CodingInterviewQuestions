package gs.interview;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Consumer;

public class IteratorImplementation<E> implements Iterable<E>{

    private List<E> list;

    public IteratorImplementation(List<E> list){
        this.list = list;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new NotImplementedException();
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new NotImplementedException();
    }

    class CustomIterator<E> implements Iterator<E>{

        private int size = list.size();
        int pointer = size - 1;

        @Override
        public boolean hasNext() {
            return pointer >= 0;
        }

        @Override
        public E next() {
            if(hasNext())
                return (E)list.get(pointer--);
            else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            list.remove(pointer--);
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            throw new NotImplementedException();
        }
    }

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 10; i++)
            list.add(i);

        IteratorImplementation<Integer> ii = new IteratorImplementation<>(list);
        Iterator<Integer> itr = ii.iterator();
        while(itr.hasNext())
            System.out.print(itr.next() + " ");
    }
}
