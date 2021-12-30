kubectl create -f deployment.yml
kubectl create -f service.yml

kubectl delete -f service.yml
kubectl delete -f deployment.yml

kubectl delete -f ms-k8-yml/deployment.yml && kubectl create -f ms-k8-yml/deployment.yml


kubectl logs user-service-pod --namespace shoppingportal

kubectl run kafka-dns-test --image=busybox --attach --rm --restart=Never -- nslookup kafka-svc