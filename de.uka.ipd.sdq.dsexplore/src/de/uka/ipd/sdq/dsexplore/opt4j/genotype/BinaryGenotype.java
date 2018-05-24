package de.uka.ipd.sdq.dsexplore.opt4j.genotype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * One can view a {@link BinaryGenotype} object as the binary analogue of a {@link Choice} object.
 * The {@link BinaryGenotype} object holds the corresponding binary representation 
 * of the value stored in a {@link Choice} object.
 * @author Hp
 *
 * @param <E>
 */
public class BinaryGenotype<E extends Number> implements BinaryGenotypeRepresentation<E> {
	
	
	// Internal Fields	
	private List<E> BinaryChoice; // It should be a list of only integer 1 and 0, instead of E 
	private TypeOfDegree DegreeType;
	
	// Constructors here
	
	// Default values here ...
	public BinaryGenotype(){
		this.BinaryChoice = new ArrayList<E>();
		this.DegreeType = BinaryGenotypeRepresentation.TypeOfDegree.AllocationDegree;
	}
	
	// A better constructor ...
	public BinaryGenotype(List<E> BinaryChoice, TypeOfDegree DegreeType){
		this.BinaryChoice = BinaryChoice;
		this.DegreeType = DegreeType;
	}
	
	@Override
	public boolean add(E e) {
		
		boolean Result = BinaryChoice.add(e);
		return Result;
	}

	@Override
	public void add(int index, E element) {
		
		BinaryChoice.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		
		return BinaryChoice.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		
		return BinaryChoice.addAll(index, c);
	}

	@Override
	public void clear() {
		
		BinaryChoice.clear();
	}

	@Override
	public boolean contains(Object o) {
		
		return BinaryChoice.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		return BinaryChoice.containsAll(c);
	}

	@Override
	public E get(int index) {
		
		return BinaryChoice.get(index);
	}

	@Override
	public int indexOf(Object o) {
		
		return BinaryChoice.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		
		return BinaryChoice.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		
		return BinaryChoice.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		
		return BinaryChoice.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		
		return BinaryChoice.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		
		return BinaryChoice.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		
		return BinaryChoice.remove(o);
	}

	@Override
	public E remove(int index) {
		
		return BinaryChoice.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		return BinaryChoice.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		
		return BinaryChoice.retainAll(c);
	}

	@Override
	public E set(int index, E element) {
		
		return BinaryChoice.set(index, element);
	}

	@Override
	public int size() {
		
		return BinaryChoice.size();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		
		return BinaryChoice.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		
		return BinaryChoice.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		
		return BinaryChoice.toArray(a);
	}

	@Override
	public void setDegreeType(
			de.uka.ipd.sdq.dsexplore.opt4j.genotype.BinaryGenotypeRepresentation.TypeOfDegree D) {
		
		DegreeType = D;
	}

	@Override
	public de.uka.ipd.sdq.dsexplore.opt4j.genotype.BinaryGenotypeRepresentation.TypeOfDegree getDegreeType() {
		
		return DegreeType;
	}
	
	public void setInternalList(List<E> list) {
		
		this.BinaryChoice = list;
	}
	
	public List<E> getInternalList() {
		
		return this.BinaryChoice;
	}

}
