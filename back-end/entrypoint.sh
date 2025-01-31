


while ! pg_isready -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" >/dev/null 2>&1; do
    echo "$(date) - Waiting for the database to be usable"
    sleep 2
done

java -jar application.jar