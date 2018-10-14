(ns the-joy-of-clojure.fp-techniques.mutation-and-concurrency)

; Concurrency vs. Parallelism
; Concurrency refers to the execution of disparate tasks at roughly the same time, each
; sharing a common resource, but not necessary performing related tasks.
; The result of concurrent tasks often affect the behavior of other concurrent tasks and
; therefore contain an element of nondeterminism.
; Parallelism refers to partitioning a task into multiple parts, each run at the same time.
; Typically parallel tasks work toward an aggregate goal, and the result of one doesn't affect
; the behavior of any other parallel tasks, thus maintaining determinacy.

; STM - Software Transactional Memory
; STM - nonblocking way to coordinate concurrent updates between related mutable value cells.

; Time - the relative moments when events occur
; State - a snapshot of an entity's properties at a moment in time
; Identity - the logical entity identified by a common stream of states occuring over time

; Behind the scenes, Clojure's STM uses multiversion concurrency control (MVCC) to ensure snapshot isolation.
; Snapshot isolation means each transaction gets its own view of the data it's interested in.
; This snapshot is made up of in-transaction reference values, forming the foundation of MVCC.

; Clojure currently provides four different reference types to aide in concurrent programming:
; refs, agents, atoms, and vars.
; All but vars are considered shared references and allow for changes to be seen across threads of execution.
;
;                 Ref    Agent    Atom    Var
; Coordinated      +      -        -       -
; Asynchronous     -      +        -       -
; Retriable        +      -        +       -
; Thread-local     -      -        -       +
;
; Coordinated means reads and writes to multiple refs can be made in a way that guarantees no race conditions.
; Asynchronous means the request to update is queued to happen in another thread some time later, while
; the thread that made the request continues immediately.
; Retriable indicates that the work done to update a reference's value is speculative and may have to be repeated.
; Thread-local means thread safety is achieved by isolating changes to state to a single thread.

; Memoization
; Memoization is a way for a function to store calculated values in a cache
; so that multiple calls to the function can retrieve previously calculated results
; from the cache instead of performing potentially expensive calculations every time.

(defn manipulable-memoize [function]
  (let [cache (atom {})]
    (with-meta
      (fn [& args]
        (or (second (find @cache args))
            (let [result (apply function args)]
              (swap! cache assoc args result)
              result)))
      {:cache cache})))
