**Ερώτηση 1**<br />
Από την περιγραφή των Broker Nodes:<br /> • “Αυτοί οι κόμβοι ενημερώνουν κατάλληλα τους publisher nodes για το ποια κλειδιά είναι υπεύθυνοι” 
 
Άρα η διαδικασία που πρέπει να ακολουθήσουμε είναι: ο κάθε publisher κάνει hash τα ονόματα των artists τοπικά, ο κάθε broker κάνει hash(ip+port) τοπικά και έπειτα ο κάθε broker στέλνει το hash value που τον αντιπροσωπεύει σε κάθε publisher, ώστε όταν έρθει η ώρα ο publisher να στείλει ένα τραγούδι κάποιου καλλιτέχνη να ξέρει ποιος broker εξυπηρετεί τον καλλιτέχνη αυτόν ? 

**Ερώτηση 2**<br />
Από την περιγραφή των Consumer Nodes:<br /> • “Γι’αυτο το λόγο θα πρέπει να ρωτάει κατάλληλα τον πρώτο τυχαίο broker για το ποιοι ειναι οι διαθέσιμοι broker που είναι υπεύθυνοι για κάποιον καλλιτέχνη κατά την πρώτη επικοινωνία του consumer με το Event Delivery System.” 
 
Δεν θα πρέπει ο κάθε καλλιτέχνης να εξυπηρετείται από έναν broker ώστε να γίνει ισοκατανομή των topics (artists)? 
 
**Ερώτηση 3**<br />
Από την περιγραφή των Consumer Nodes & 4o bullet για την υλοποίηση του Event Delivery System: <br />
• “Γι’αυτο το λόγο θα πρέπει να ρωτάει κατάλληλα τον πρώτο τυχαίο broker για το ποιοι ειναι οι διαθέσιμοι broker που είναι υπεύθυνοι για κάποιον καλλιτέχνη κατά την πρώτη επικοινωνία του consumer με το Event Delivery System.” 
 
• Όταν λάβει o broker node ένα query από τον χρήστη, αρχικά κοιτά αν είναι ήδη συνδεδεμένος μαζί του (αν έχει γίνει register ο consumer node στον συγκεκριμένο broker και αν έχει κάνει login).  Αν υπάρχει ήδη σύνδεση, τότε επιτρέπει στον χρήστη να ζητήσει και να κάνει pull το κατάλληλο κομμάτι.  Αν όχι, τότε δημιουργείται μια νέα σύνδεση και παράλληλα επιστρέφεται στον consumer η λίστα με τους υπόλοιπους brokers και τα κλειδιά για τα οποία είναι υπεύθυνοι. 
 
Δηλαδή ο consumer για να ρωτήσει τον πρώτο τυχαίο broker πρέπει πρώτα να κάνει register σε αυτόν? 
Αν αυτός ο πρώτος τυχαίος broker είναι ο κατάλληλος γίνεται το pull αλλιώς επιστρέφεται στον consumer η λίστα μέσω της οποίας ο consumer εντοπίζει τον κατάλληλο broker ? 
Όταν εντοπίσει τον κατάλληλο broker πρέπει να κάνει register o consumer σε αυτόν ώστε να μπορεί να γίνει το pull ? 