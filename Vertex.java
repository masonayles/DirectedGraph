/**
 * Class Vertex<V> represents a vertex in a graph with a label of generic type.
 *
 * @param <V> the type of the vertex label
 */
public class Vertex <V>
{
    private V label;

    /**
     * Constructs a new Vertex with the given label.
     *
     * @param label The label of the vertex
     * @throws IllegalArgumentException if the label is null
     */
    public void Vertex(V label)
    {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null.");
        }
        this.label = label;
    }

    /**
     * Retrieves the label of the vertex.
     *
     * @return The label of this vertex
     */
    public V getLabel()
    {
        return label;
    }

    /**
     * Sets the label of the vertex.
     *
     * @param label The new label for this vertex
     * @throws IllegalArgumentException if the new label is null
     */
    public void setLabel(V label) {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null.");
        }
        this.label = label;
    }

    /**
     * Compares this vertex to the specified object. The result is true if and only if the argument
     * is not null and is a Vertex object that has the same label as this object.
     *
     * @param o The object to compare this Vertex against
     * @return true if the given object represents a Vertex equivalent to this vertex, false otherwise
     */
    public boolean equals(Vertex<V> o)
    {
        if (this ==o) return true;
        if (o == null) return false;

        if (label == null)
        {
            return o.getLabel() == null;
        }
        else
        {
            return label.equals(o.getLabel());
        }
    }

    /**
     * Returns a hash code for this vertex. Did a good bit of research on WSSCHOOLS and I thought this
     * would be a good idea for the structure of the program.
     * The hash code for a Vertex object is computed as
     * the hash code of the label, as defined by the hashCode method of the Object class.
     *
     * @return a hash code value for this Vertex.
     */
    @Override
    public int hashCode()
    {
        if (label == null)
        {
            return 0;
        }
        else
        {
            return label.hashCode();
        }
    }

    /**
     * Returns a string representation of this vertex which includes its label.
     *
     * @return a string representation of this vertex
     */
    @Override
    public String toString()
    {
        return "Vertex{" + "label=" + label + '}';
    }
}
