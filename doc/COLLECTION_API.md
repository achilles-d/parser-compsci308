Achilles Dabrowski (ajd66), Achintya Kumar (ask60), Saurav Sanjay (sks61), Abebe Amare(ama100)

- In your experience using these collections, are they hard or easy to use?

Our group unanimously agreed that the Collection API is easy to use. Though, we did
agree that this might be from the fact that we have so much experience with them as
they are taught as a standard from the most basic CS classes. 
- In your experience using these collections, do you feel mistakes are easy to avoid?

Yes we do feel mistakes are easy to avoid because the method names are descriptive
and the error messages/documentation is easy to understand.

- How many interfaces do specific concrete collection classes implement (such as LinkedList)? What do you think is the purpose of each interface?

The linked list class implements 7 total interfaces. The Iterable interface probably
allows for the enhanced for loop to iterate through it. The cloneable interface
probably allows for implementation of the feature that allows you to make a copy of
your current linked list. A collection probably allows the feature where you can
bring together objects into a group and the list probably provides some sort of order
to the group. The deque and que interfaces probably help you add and remove stuff
from the linked list in order. Serializable probably implements functionality to
let objects be serialized.

- How many different implementations are there for a specific collection class (such as Set)? Do you think the number justifies it being an interface or not?

Set has 9 interfaces. This does justify there being interfaces because you can
implement as many interfaces as possible. An alternative often considered is an 
abstract class but you can only extend one class at a time. 

- How many levels of superclasses do specific concrete collection classes have? What do you think is the purpose of each inheritance level?

After Object there are usually 2 to 3 levels of superclasses. At each inheritance
level there mush be a major shift in the idea of what the structure of the collection
should be. For example, ArrayList has AbstractCollection and AbstractList. These
are broad ideas where it is said in Collection there should be a group of objects
and then in List that it should be ordered in some way. Whereas HashSet also gets 
AbstractCollection and then AbstractSet. Since a list and set are different ideas
for listing values at the second level they differ, but they are still group objects
so at the top they both get collection. 

- Why does it make sense to have the utility classes instead of adding that functionality to the collection types themselves? Are there any overlapping methods (ones that are in both a specific collection and a utility class)? If so, is there any guidance on which one you should use?

It makes sense to have this because many functions across other classes will be similar
and so it reduces code duplication. In HashSet there are some overlapping methods. From
both Collection and Set it gets addAll, containsAll, and retainsAll. It is probably
best to use method from lowest level so that it implements the functionality closest
to what you're looking for.s 