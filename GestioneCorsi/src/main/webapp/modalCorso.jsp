<div class="modal fade" id="newCorso" role="dialog">
	<div class="modal-dialog modal-md" role="dialog">
		<div class="modal-content">
			<form action="#" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Nuovo corso</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text"
							class="form-control" name="nome">
					</div>
					<div class="form-group">
						<label for="dataInizio">Data inizio</label> <input type="date"
							class="form-control" name="dataInizio">
					</div>
					<div class="form-group">
						<label for="dataFine">Data fine</label> <input type="date"
							class="form-control" name="dataFine">
					</div>
					<div class="form-group">
						<label for="costo">Costo</label> <input type="number"
							class="form-control" name="costo" step="0.01" min="0">
					</div>
					<div class="form-group">
						<label for="commenti">Commenti</label> <input type="text"
							class="form-control" name="commenti">
					</div>
					<div class="form-group">
						<label for="aula">Aula</label> <input type="text"
							class="form-control" name="aula">
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success btn-lg">Crea</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
				</div>
			</form>
		</div>
	</div>
</div>
